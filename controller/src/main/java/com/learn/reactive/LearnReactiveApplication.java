package com.learn.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan({"com.learn.reactive","com.learn.reactive.service",
        "com.learn.reactive.service.impl","com.learn.reactive.repository"})
@EnableMongoRepositories("com.learn.reactive.repository")
public class LearnReactiveApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearnReactiveApplication.class, args);
    }

}
