package com.learn.reactive.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan({"com.learn.reactive.app","com.learn.reactive.service"})
@EnableMongoRepositories("com.learn.reactive.repository")
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
public class LearnReactiveApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearnReactiveApplication.class, args);
    }

}
