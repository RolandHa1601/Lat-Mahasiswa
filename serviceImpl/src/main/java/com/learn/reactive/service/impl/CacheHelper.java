package com.learn.reactive.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CacheHelper {

  @Autowired
  private ReactiveRedisTemplate<String, String> redisTemplate;

  private ReactiveValueOperations<String, String> operations;

  private String objectWriter(Object value) {
    try {
      return new ObjectMapper().writeValueAsString(value);
    } catch (JsonProcessingException e) {
      log.error("Error on write object.", e);
      return "";
    }
  }

  public <T> Mono<Boolean> createCache(String key, T value, long ttl) {
    operations = redisTemplate.opsForValue();
    return Mono.just(value)
        .map(this::objectWriter)
        .filter(s -> !s.isEmpty())
        .flatMap(s -> ttl > 0 ? operations.set(key, s, Duration.ofSeconds(ttl))
            : operations.set(key, s))
        .switchIfEmpty(Mono.just(false))
        .onErrorResume(throwable -> {
          log.error("Error on createCache. key = {}, error = {}", key, throwable);
          return Mono.just(false);
        });
  }

  public <T> Mono<Boolean> deleteCache(String key) {
    operations = redisTemplate.opsForValue();
    return Mono.just(key)
        .flatMap(key1 -> operations.delete(key1))
        .onErrorResume(throwable -> {
          log.error("Error on deleteCache. key = {}, error = {}", key, throwable);
          return Mono.just(false);
        });
  }

  public  <T> T objectParser(String value, Class<T> clazz) {
    try {
      return new ObjectMapper().readValue(value, clazz);
    } catch (IOException throwable) {
      log.error("Error on parse Object. value = {}, error = {}", value, throwable);
      return null;
    }
  }

  public  <T> Mono<T> findCache(String key, Class<T> clazz) {
    operations = redisTemplate.opsForValue();
    return operations
        .get(key)
        .map(value -> objectParser(value, clazz))
        .onErrorResume(throwable -> {
          log.error("Error on findCacheByKey. key = {}, error = {}", key, throwable);
          return Mono.empty();
        });
  }

}
