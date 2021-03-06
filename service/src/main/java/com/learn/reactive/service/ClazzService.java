package com.learn.reactive.service;

import com.learn.reactive.model.dao.Clazz;
import com.learn.reactive.model.dto.CheckTicketStatusRequest;
import com.learn.reactive.model.dto.CheckTicketStatusResponse;
import com.learn.reactive.model.dto.ClazzRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClazzService {

  Mono<Clazz> create(ClazzRequest clazzRequest);

  Mono<Clazz> update(String id, ClazzRequest clazzRequest);

  Mono<CheckTicketStatusResponse> checkTicketStatus(CheckTicketStatusRequest checkTicketStatusRequest);

  Mono<Void> delete(String id);

  Mono<Clazz> findById(String id);

  Flux<Clazz> findAll();
}
