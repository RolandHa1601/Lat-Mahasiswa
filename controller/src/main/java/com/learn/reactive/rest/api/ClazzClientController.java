package com.learn.reactive.rest.api;

import com.learn.reactive.model.dao.Clazz;
import com.learn.reactive.model.dto.CheckTicketStatusRequest;
import com.learn.reactive.model.dto.CheckTicketStatusResponse;
import com.learn.reactive.model.dto.ClazzRequest;
import com.learn.reactive.service.ClazzService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/class-client")
public class ClazzClientController {

  @Autowired
  @Qualifier("clazzClientService")
  private ClazzService clazzService;

  @PostMapping
  public Mono<Clazz> create(@RequestBody ClazzRequest clazzRequest) {
    return clazzService.create(clazzRequest);
  }

  @PutMapping("/{id}")
  public Mono<Clazz> update(@PathVariable("id") String id,
      @RequestBody ClazzRequest clazzRequest) {
    return clazzService.update(id, clazzRequest);
  }

  @DeleteMapping("/{id}")
  public Mono<Void> delete(@PathVariable("id") String id) {
    return clazzService.delete(id);
  }

  @GetMapping("/id/{id}")
  public Mono<Clazz> findById(@PathVariable("id") String id) {
    return clazzService.findById(id);
  }

  @GetMapping("/all")
  public Mono<List<Clazz>> findAll() {
    return clazzService.findAll()
        .collectList();
  }

  @PostMapping(path = "/ticket-status")
  public Mono<CheckTicketStatusResponse> getAirAsiaTiketStatus(@RequestBody
      CheckTicketStatusRequest checkTicketStatusRequest
  ) {
    return clazzService.checkTicketStatus(checkTicketStatusRequest);
//        .subscribeOn(getAirAsiaStatus);
  }


}
