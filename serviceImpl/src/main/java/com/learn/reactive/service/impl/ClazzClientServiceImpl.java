package com.learn.reactive.service.impl;

import com.learn.reactive.exception.LRException;
import com.learn.reactive.model.CheckInResponse;
import com.learn.reactive.model.dao.Clazz;
import com.learn.reactive.model.dto.CheckTicketStatusRequest;
import com.learn.reactive.model.dto.CheckTicketStatusResponse;
import com.learn.reactive.model.dto.ClazzRequest;
import com.learn.reactive.service.ClazzService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field.Str;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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
  private final WebClient client2;

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

    this.client2 = clientBuilder.clone()
        .clientConnector(clientConnector)
        .baseUrl("https://k.airasia.com/checkin/validation/booking/0/0")
        .defaultHeaders(httpHeaders -> {
//          httpHeaders.add(HttpHeaders.ORIGIN, "http://localhost:8082/class");
//          httpHeaders.add(HttpHeaders.REFERER, "http://localhost:8082/class");
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
        .uri("/")
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
  public Mono<CheckTicketStatusResponse> checkTicketStatus(CheckTicketStatusRequest checkTicketStatusRequest) {
    log.info("HTTP {} Request to {}, with {}", HttpMethod.POST, "https://k.airasia.com/checkin/validation/booking/0/0",  checkTicketStatusRequest);
    checkTicketStatusRequest.setOriginCode(null);
    checkTicketStatusRequest.setIsUpdationRequired(Boolean.TRUE);
    return client2.post()
        .uri("/" )
        .headers(httpHeaders -> {
          httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
          httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        })
        .body(Mono.just(checkTicketStatusRequest), CheckTicketStatusRequest.class)
        .exchangeToMono(clientResponse -> this
            .validateResponse(clientResponse)
            .flatMap(response -> response.toEntity(CheckTicketStatusResponse.class))
//            .doOnNext(s -> log.info("{}" , s.getBody().getJourneys().get(0).getSegments().get(0).getPassengers().get(0)))
        )
        .doOnNext(clazz -> log
            .info("HTTP {} Response to {}/{}, with {}", HttpMethod.POST, "https://k.airasia.com/checkin/validation/booking/0/0", checkTicketStatusRequest))
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
    log.info("HTTP {} Request to {}", HttpMethod.GET, BASE_URL);
    return client.get()
        .uri("/all")
        .headers(httpHeaders -> {
          httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
          httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        })
        .exchangeToFlux(clientResponse -> this
            .validateResponses(clientResponse)
            .flatMap(response -> response.toEntity(Clazz.class)))
        .doOnNext(clazz -> log
            .info("HTTP {} Response to {}/all, with {}", HttpMethod.GET, BASE_URL, clazz))
        .map(ResponseEntity::getBody);
  }

  private Mono<ClientResponse> validateResponse(ClientResponse clientResponse) {
//    log.info(clientResponse.statusCode().is5xxServerError() + "" + clientResponse.statusCode().is2xxSuccessful());
    if (!clientResponse.statusCode().is2xxSuccessful()) {
      return Mono.error(new LRException("HTTP Request failed"));
    }
    return Mono.just(clientResponse);
  }

  private Flux<ClientResponse> validateResponses(ClientResponse clientResponse) {
    if (!clientResponse.statusCode().is2xxSuccessful()) {
      return Flux.error(new LRException("HTTP Request failed"));
    }
    return Flux.just(clientResponse);
  }
}
