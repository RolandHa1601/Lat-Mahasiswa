package com.learn.reactive;

import com.learn.reactive.model.Mahasiswa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootApplication
@ComponentScan({"com.learn.reactive","com.learn.reactive.service",
        "com.learn.reactive.service.impl","com.learn.reactive.repository"})
@EnableMongoRepositories("com.learn.reactive.repository")
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
public class LearnReactiveApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearnReactiveApplication.class, args);
    }

}
