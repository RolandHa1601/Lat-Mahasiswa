package com.learn.reactive;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {
  @Bean
  public WebClient webClient() {
    return WebClient.builder()
//        .baseUrl("")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//        .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
        .defaultHeader(HttpHeaders.ACCEPT, "application/json, text/javascript, */*; q=0.01")
//        .defaultHeader(HttpHeaders.AUTHORIZATION, "Token bee193f962a14bff6ac171a86126cef3")
        .build();
  }

}
