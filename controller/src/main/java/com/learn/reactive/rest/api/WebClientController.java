package com.learn.reactive.rest.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.reactive.model.Mahasiswa;
import com.learn.reactive.model.RefundData;
import com.learn.reactive.model.RequestBody;
import com.learn.reactive.model.Response;
import com.learn.reactive.service.impl.CacheHelper;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class WebClientController {
  @Autowired
  WebClient webClient;

  @Autowired
  private CacheHelper cacheHelper;

  @GetMapping("/getClient")
  public Mono<String> findAll()
  {
//    HttpHeaders httpHeaders = new HttpHeaders();
//    httpHeaders.add(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36");
//    httpHeaders.add(HttpHeaders.COOKIE, "_ga=GA1.2.2088613896.1628059782; _mkto_trk=id:048-SZW-045&token:_mch-spring.io-1628148403529-22239; _gcl_au=1.1.2103421591.1628148404; _fbp=fb.1.1628148403587.465336846; AAMC_vmwareinc_0=REGION%7C3; aam_uuid=34240968714128756543158667673853663175; __adroll_fpc=bb5ee2d973c79c75e473f8200eed676b-1628148404282; _gid=GA1.2.260682588.1628475697; AMCVS_5B29123F5245AD520A490D45%40AdobeOrg=1; s_tbm1=true; s_cc=true; utag_main=v_id:017b15350b78009818b646cbaad003079002907100942$_sn:4$_ss:1$_st:1628580834316$_pn:1%3Bexp-session$ses_id:1628579034316%3Bexp-session; _uetsid=1703ec70f8f011eba7282fc25d63fbe5; _uetvid=7bdc5880f5be11ebb4c18b9b7874ec19; AMCV_5B29123F5245AD520A490D45%40AdobeOrg=1585540135%7CMCIDTS%7C18849%7CMCMID%7C26665506321475243552400559171677637954%7CMCAAMLH-1629183834%7C3%7CMCAAMB-1629183834%7CRKhpRz8krg2tLO6pguXWp5olkAcUniQYPHaMWWgdJ3xzPWQmdj0y%7CMCOPTOUT-1628586234s%7CNONE%7CvVersion%7C4.4.0; s_tbm=true; s_dse=https%3A%2F%2Fwww.google.com%2F%3Avmware%20%3A%20spring%20%3A%20projects%20%3A%20spring-boot%3AGoogle%3ANatural%20Search; OptanonConsent=isIABGlobal=false&datestamp=Tue+Aug+10+2021+14%3A03%3A55+GMT%2B0700+(Western+Indonesia+Time)&version=6.15.0&hosts=&consentId=c4f17ee5-e9c1-4ff5-b948-da64ae7a846c&interactionCount=1&landingPath=NotLandingPage&groups=C0001%3A1%2CC0002%3A1%2CC0003%3A1%2CC0004%3A1%2CC0005%3A1&AwaitingReconsent=false; __ar_v4=4RMH4B3GVNF6PPORXRHP7H%3A20210804%3A4%7C3T3E3J57XBHLNN2SF6MDM3%3A20210804%3A4%7COSU6T4K5BNEFDBKAQHSKNI%3A20210804%3A4; s_tp=2944; s_ppv=vmware%2520%253A%2520spring%2520%253A%2520projects%2520%253A%2520spring-boot%2C29%2C25%2C852");
    Mono<String> res = webClient.get()
        .uri("https://spring.io/projects/spring-boot")
        .header(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36")
        .header(HttpHeaders.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
        .retrieve()
        .bodyToMono(String.class);
    return res;
  }

  @GetMapping("/postAll")
  public Mono<RefundData> postAll()
  {

//    HttpHeaders httpHeaders = new HttpHeaders();
//    httpHeaders.add(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36");
//    httpHeaders.add(HttpHeaders.COOKIE, "_ga=GA1.2.2088613896.1628059782; _mkto_trk=id:048-SZW-045&token:_mch-spring.io-1628148403529-22239; _gcl_au=1.1.2103421591.1628148404; _fbp=fb.1.1628148403587.465336846; AAMC_vmwareinc_0=REGION%7C3; aam_uuid=34240968714128756543158667673853663175; __adroll_fpc=bb5ee2d973c79c75e473f8200eed676b-1628148404282; _gid=GA1.2.260682588.1628475697; AMCVS_5B29123F5245AD520A490D45%40AdobeOrg=1; s_tbm1=true; s_cc=true; utag_main=v_id:017b15350b78009818b646cbaad003079002907100942$_sn:4$_ss:1$_st:1628580834316$_pn:1%3Bexp-session$ses_id:1628579034316%3Bexp-session; _uetsid=1703ec70f8f011eba7282fc25d63fbe5; _uetvid=7bdc5880f5be11ebb4c18b9b7874ec19; AMCV_5B29123F5245AD520A490D45%40AdobeOrg=1585540135%7CMCIDTS%7C18849%7CMCMID%7C26665506321475243552400559171677637954%7CMCAAMLH-1629183834%7C3%7CMCAAMB-1629183834%7CRKhpRz8krg2tLO6pguXWp5olkAcUniQYPHaMWWgdJ3xzPWQmdj0y%7CMCOPTOUT-1628586234s%7CNONE%7CvVersion%7C4.4.0; s_tbm=true; s_dse=https%3A%2F%2Fwww.google.com%2F%3Avmware%20%3A%20spring%20%3A%20projects%20%3A%20spring-boot%3AGoogle%3ANatural%20Search; OptanonConsent=isIABGlobal=false&datestamp=Tue+Aug+10+2021+14%3A03%3A55+GMT%2B0700+(Western+Indonesia+Time)&version=6.15.0&hosts=&consentId=c4f17ee5-e9c1-4ff5-b948-da64ae7a846c&interactionCount=1&landingPath=NotLandingPage&groups=C0001%3A1%2CC0002%3A1%2CC0003%3A1%2CC0004%3A1%2CC0005%3A1&AwaitingReconsent=false; __ar_v4=4RMH4B3GVNF6PPORXRHP7H%3A20210804%3A4%7C3T3E3J57XBHLNN2SF6MDM3%3A20210804%3A4%7COSU6T4K5BNEFDBKAQHSKNI%3A20210804%3A4; s_tp=2944; s_ppv=vmware%2520%253A%2520spring%2520%253A%2520projects%2520%253A%2520spring-boot%2C29%2C25%2C852");
    Mono<RefundData> res = webClient.post()
//        .uri("https://api.mocklab.io/proxy/mock-api/flight-refund.mocklab.io/__admin/test-request")
        .uri("http://flight-refund.mocklab.io/tix-flight-refund/refundStatus")
        .header(HttpHeaders.CONTENT_TYPE, "application/json")
        .retrieve()
        .bodyToMono(String.class)
        .map(s -> cacheHelper.objectParser(s,Response.class))
        .map(Response::getData);
    return res;
  }

  @GetMapping("/postAllAdminRequest")
  public Mono<String> postAllAdminRequest()
  {
    RequestBody request = RequestBody
        .builder()
        .method("post")
        .url("/tix-flight-refund/refundStatus")
        .bodyAs64("")
        .build();
    Mono<String> res = webClient.post()
        .uri("https://api.mocklab.io/proxy/mock-api/flight-refund.mocklab.io/__admin/test-request")
        .body(Mono.just(request), RequestBody.class)
        .retrieve().bodyToMono(String.class);
//        .map(s -> cacheHelper.objectParser(s,Response.class))
//        .map(Response::getData);
    return res;
  }

  @PostMapping("/internal/post")
  public Mono<Mahasiswa> postToApiInternal(Mahasiswa mahasiswa)
  {
    return webClient.post()
//        .uri("https://api.mocklab.io/proxy/mock-api/flight-refund.mocklab.io/__admin/test-request")
        .uri(uriBuilder -> uriBuilder
                .path("localhost:8082/mahasiswa/save")
            .queryParam("name", mahasiswa.getName())
            .queryParam("nim", mahasiswa.getNim())
            .queryParam("GPA", mahasiswa.getGPA())
            .build())
        .header(HttpHeaders.ACCEPT , "*/*")
//        .header(HttpHeaders.USER_AGENT, "PostmanRuntime/7.28.3")
//        .header(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br")
        .retrieve()
        .bodyToMono(Mahasiswa.class);
  }

  @GetMapping("internal/getOne/{nim}")
  public Mono<Mahasiswa> getFromApiInternal(@PathVariable String nim)
  {
    return webClient.get()
//        .uri("https://api.mocklab.io/proxy/mock-api/flight-refund.mocklab.io/__admin/test-request")
        .uri(uriBuilder -> uriBuilder
            .path("localhost:8082/mahasiswa/get/{nim}")
            .build(nim))
        .header(HttpHeaders.ACCEPT , "*/*")
//        .header(HttpHeaders.USER_AGENT, "PostmanRuntime/7.28.3")
//        .header(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br")
        .retrieve()
        .bodyToMono(Mahasiswa.class);
  }

  @GetMapping("internal/getAll")
  public Flux<Mahasiswa> getAllFromInternal()
  {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("localhost:8082/mahasiswa/getAll")
            .build())
        .header(HttpHeaders.ACCEPT , "*/*")
        .retrieve()
        .bodyToFlux(Mahasiswa.class);
  }

  @DeleteMapping("internal/delete/{nim}")
  public Mono<String> deleteFromInternal(@PathVariable String nim)
  {
    return webClient.delete()
        .uri(uriBuilder -> uriBuilder
            .path("localhost:8082/mahasiswa/delete/{nim}")
            .build(nim))
        .header(HttpHeaders.ACCEPT , "*/*")
        .retrieve()
        .bodyToMono(String.class);
  }

  @PutMapping("internal/update")
  public Mono<Mahasiswa> updateFromInternal(Mahasiswa mahasiswa)
  {
    return webClient.put()
        .uri(uriBuilder -> uriBuilder
            .path("localhost:8082/mahasiswa/update")
            .queryParam("name", mahasiswa.getName())
            .queryParam("nim", mahasiswa.getNim())
            .queryParam("GPA", mahasiswa.getGPA())
            .build())
        .header(HttpHeaders.ACCEPT , "*/*")
        .retrieve()
        .bodyToMono(Mahasiswa.class);
  }


}
