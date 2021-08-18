package com.learn.reactive.rest.api;

import com.learn.reactive.model.Mahasiswa;
import com.learn.reactive.model.RefundData;
import com.learn.reactive.model.RequestBody;
import com.learn.reactive.model.Response;
import com.learn.reactive.service.impl.CacheHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.KafkaClient;
import org.apache.kafka.common.protocol.types.Field.Str;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.messaging.Message;

@RestController
@Slf4j
public class WebClientController {

  @Autowired
  WebClient webClient;

  @Autowired
  private CacheHelper cacheHelper;

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @GetMapping("/getClient")
  public Mono<String> findAll() {
    return webClient.get()
        .uri("https://spring.io/projects/spring-boot")
        .header(HttpHeaders.USER_AGENT,
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36")
        .header(HttpHeaders.ACCEPT,
            "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
        .retrieve()
        .bodyToMono(String.class);
  }

  @GetMapping("/postAll")
  public Mono<RefundData> postAll() {

    return webClient.post()
        .uri("http://flight-refund.mocklab.io/tix-flight-refund/refundStatus")
        .header(HttpHeaders.CONTENT_TYPE, "application/json")
        .retrieve()
        .bodyToMono(String.class)
        .map(s -> cacheHelper.objectParser(s, Response.class))
        .map(Response::getData);
  }

//  @GetMapping("/postAllAdminRequest")
//  public Mono<String> postAllAdminRequest() {
//    RequestBody request = RequestBody
//        .builder()
//        .method("post")
//        .url("/tix-flight-refund/refundStatus")
//        .bodyAs64("")
//        .build();
//    return webClient.post()
//        .uri("https://api.mocklab.io/proxy/mock-api/flight-refund.mocklab.io/__admin/test-request")
//        .body(Mono.just(request), RequestBody.class)
//        .retrieve().bodyToMono(String.class);
//  }

  @PostMapping("/internal/post")
  public Mono<Mahasiswa> postToApiInternal(Mahasiswa mahasiswa) {
    return webClient.post()
        .uri(uriBuilder -> uriBuilder
            .path("localhost:8082/mahasiswa/save")
//            .queryParam("name", mahasiswa.getName())
//            .queryParam("nim", mahasiswa.getNim())
//            .queryParam("GPA", mahasiswa.getGPA())
            .build())
        .header(HttpHeaders.ACCEPT, "*/*")
        .body(Mono.just(mahasiswa), Mahasiswa.class)
        .retrieve()
        .bodyToMono(Mahasiswa.class);
  }

//  @PostMapping("/kafka/send")
//  public Mono<String> sendToKafka(String sss) {
//    return Mono.just(sss)
//            .doOnNext(msg -> kafkaTemplate.send("learn.reactive", msg))
//        .thenReturn("ok");
//
//      }

  @GetMapping("internal/getOne/{nim}")
  public Mono<Mahasiswa> getFromApiInternal(@PathVariable String nim) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("localhost:8082/mahasiswa/get/{nim}")
            .build(nim))
        .header(HttpHeaders.ACCEPT, "*/*")
        .retrieve()
        .bodyToMono(Mahasiswa.class);
  }

  @GetMapping("internal/getAll")
  public Flux<Mahasiswa> getAllFromInternal() {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("localhost:8082/mahasiswa/getAll")
            .build())
        .header(HttpHeaders.ACCEPT, "*/*")
        .retrieve()
        .bodyToFlux(Mahasiswa.class);
  }

  @DeleteMapping("internal/delete/{nim}")
  public Mono<String> deleteFromInternal(@PathVariable String nim) {
    return webClient.delete()
        .uri(uriBuilder -> uriBuilder
            .path("localhost:8082/mahasiswa/delete/{nim}")
            .build(nim))
        .header(HttpHeaders.ACCEPT, "*/*")
        .retrieve()
        .bodyToMono(String.class);
  }

  @PutMapping("internal/update")
  public Mono<Mahasiswa> updateFromInternal(Mahasiswa mahasiswa) {
    return webClient.put()
        .uri(uriBuilder -> uriBuilder
            .path("localhost:8082/mahasiswa/update")
            .queryParam("name", mahasiswa.getName())
            .queryParam("nim", mahasiswa.getNim())
            .queryParam("GPA", mahasiswa.getGPA())
            .build())
        .header(HttpHeaders.ACCEPT, "*/*")
        .body(mahasiswa , Mahasiswa.class)
        .retrieve()
        .bodyToMono(Mahasiswa.class);
  }


}
