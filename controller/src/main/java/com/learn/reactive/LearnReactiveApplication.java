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

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<?, ?> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        return template;
    }

    @Bean
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
        return new LettuceConnectionFactory("localhost", 6379);
    }

    @Bean
    public ReactiveRedisTemplate<String, Mahasiswa> reactiveRedisTemplate(
        ReactiveRedisConnectionFactory factory) {
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<Mahasiswa> valueSerializer =
            new Jackson2JsonRedisSerializer<>(Mahasiswa.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, Mahasiswa> builder =
            RedisSerializationContext.newSerializationContext(keySerializer);
        RedisSerializationContext<String, Mahasiswa> context =
            builder.value(valueSerializer).build();
        return new ReactiveRedisTemplate<>(factory, context);
    }

    public static void main(String[] args) {
        SpringApplication.run(LearnReactiveApplication.class, args);
    }

}
