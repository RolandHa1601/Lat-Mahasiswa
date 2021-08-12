package com.learn.reactive.service.impl;

import com.learn.reactive.exception.LRException;
import com.learn.reactive.model.dao.Clazz;
import com.learn.reactive.model.dto.ClazzRequest;
import com.learn.reactive.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Slf4j
@Service("clazzClientService")
public class ClazzClientServiceImpl implements ClazzService {

  private final String BASE_URL = "http://localhost:8082/class";
  private final WebClient client;

  public ClazzClientServiceImpl(WebClient.Builder clientBuilder) {

    ReactorClientHttpConnector clientConnector = new ReactorClientHttpConnector(HttpClient.create()
        .compress(true));

    this.client = clientBuilder.clone()
        .clientConnector(clientConnector)
        .baseUrl(BASE_URL)
        .defaultHeaders(httpHeaders -> {
          httpHeaders.add(HttpHeaders.ORIGIN, "http://localhost:8082/class");
          httpHeaders.add(HttpHeaders.REFERER, "http://localhost:8082/class");
          httpHeaders.add(HttpHeaders.USER_AGENT,
              "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36");
        })
        .build();
  }

  @Override
  public Mono<Clazz> create(ClazzRequest clazzRequest) {
    log.info("HTTP {} Request to {}, with data {}", HttpMethod.POST, BASE_URL, clazzRequest);
    return client.post()
        .uri("")
        .headers(httpHeaders -> {
          httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
          httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        })
        .body(BodyInserters.fromValue(clazzRequest))
        .exchangeToMono(clientResponse -> this
            .validateResponse(clientResponse)
            .flatMap(response -> response.toEntity(Clazz.class)))
        .doOnNext(clazz -> log
            .info("HTTP {} Response to {}, with {}", HttpMethod.POST, BASE_URL, clazz))
        .map(ResponseEntity::getBody);
  }

  @Override
  public Mono<Clazz> update(String id, ClazzRequest clazzRequest) {
    log.info("HTTP {} Request to {}/{}, with {}", HttpMethod.PUT, BASE_URL, id, clazzRequest);
    return client.put()
        .uri("/" + id)
        .headers(httpHeaders -> {
          httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
          httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        })
        .body(BodyInserters.fromValue(clazzRequest))
        .exchangeToMono(clientResponse -> this
            .validateResponse(clientResponse)
            .flatMap(response -> response.toEntity(Clazz.class)))
        .doOnNext(clazz -> log
            .info("HTTP {} Response to {}/{}, with {}", HttpMethod.PUT, BASE_URL, id, clazz))
        .map(ResponseEntity::getBody);
  }

  @Override
  public Mono<Void> delete(String id) {
    log.info("HTTP {} Request to {}/{}", HttpMethod.DELETE, BASE_URL, id);
    return client.delete()
        .uri("/" + id)
        .headers(httpHeaders -> {
          httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
          httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        })
        .exchangeToMono(clientResponse -> this
            .validateResponse(clientResponse)
            .flatMap(response -> response.toEntity(Clazz.class)))
        .doOnNext(clazz -> log
            .info("HTTP {} Response to {}/{}", HttpMethod.DELETE, BASE_URL, id))
        .then();
  }

  @Override
  public Mono<Clazz> findById(String id) {
    log.info("HTTP {} Request to {}, with id {}", HttpMethod.GET, BASE_URL, id);
    return client.get()
        .uri("/id/" + id)
        .headers(httpHeaders -> {
          httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
          httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        })
        .exchangeToMono(clientResponse -> this
            .validateResponse(clientResponse)
            .flatMap(response -> response.toEntity(Clazz.class)))
        .doOnNext(clazz -> log
            .info("HTTP {} Response to {}/id/{}, with {}", HttpMethod.GET, BASE_URL, id, clazz))
        .map(ResponseEntity::getBody);
  }

  @Override
  public Flux<Clazz> findAll() {
    return null;
  }

  private Mono<ClientResponse> validateResponse(ClientResponse clientResponse) {
    if (!clientResponse.statusCode().is2xxSuccessful()) {
      return Mono.error(new LRException("HTTP Request failed"));
    }
    return Mono.just(clientResponse);
  }
}
