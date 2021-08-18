package com.learn.reactive.service.impl;

import com.learn.reactive.exception.LRException;
import com.learn.reactive.model.dao.Clazz;
import com.learn.reactive.model.dto.CheckTicketStatusRequest;
import com.learn.reactive.model.dto.CheckTicketStatusResponse;
import com.learn.reactive.model.dto.ClazzRequest;
import com.learn.reactive.repository.ClazzRepository;
import com.learn.reactive.service.ClazzService;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service("clazzService")
public class ClazzServiceImpl implements ClazzService {

  private static final int ACTIVE = 0;
  private static final int DELETED = 1;
  private static final long CACHE_TTL = 30000;


  @Autowired
  private CacheHelper cacheHelper;

  @Autowired
  private ClazzRepository clazzRepository;

  @Override
  public Mono<Clazz> create(ClazzRequest clazzRequest) {
    return clazzRepository.findByIdAndIsDeleted(clazzRequest.getCode(), ACTIVE)
        .doOnNext(clazz -> {
          log.error("Class id is used {}", clazzRequest);
          throw new LRException("Class id is used");
        })
        .switchIfEmpty(Mono
            .just(this.constructClazz(clazzRequest))
            .flatMap(clazzRepository::save)
            .flatMap(
                clazz -> cacheHelper.createCache(this.cacheKey(clazz.getId()), clazz, CACHE_TTL)
                    .thenReturn(clazz))
            .doOnNext(clazz -> log.info("Success saved class {}", clazz)));
  }

  @Override
  public Mono<Clazz> update(String id, ClazzRequest clazzRequest) {
    return clazzRepository.findByIdAndIsDeleted(id, ACTIVE)
        .doOnNext(clazz -> {
          clazz.setId(clazz.getId());
          clazz.setName(clazz.getName());
          clazz.setUpdateDate(new Date());
        })
        .flatMap(clazzRepository::save)
        .flatMap(clazz -> cacheHelper.createCache(this.cacheKey(clazz.getId()), clazz, CACHE_TTL)
            .thenReturn(clazz))
        .doOnNext(clazz -> log.info("Success updated class {}", clazz))
        .switchIfEmpty(Mono.defer(() -> Mono.error(new LRException("Class id not found"))));
  }

  @Override
  public Mono<CheckTicketStatusResponse> checkTicketStatus(CheckTicketStatusRequest checkTicketStatusRequest) {
    return Mono.just(CheckTicketStatusResponse.builder().build());
  }

  @Override
  public Mono<Void> delete(String id) {
    return clazzRepository.findByIdAndIsDeleted(id, ACTIVE)
        .doOnNext(clazz -> {
          clazz.setUpdateDate(new Date());
          clazz.setIsDeleted(DELETED);
        })
        .flatMap(clazzRepository::save)
        .flatMap(clazz -> cacheHelper.deleteCache(this.cacheKey(clazz.getId()))
            .thenReturn(clazz))
        .doOnNext(clazz -> log.info("Success deleted class {}", clazz))
        .switchIfEmpty(Mono.defer(() -> Mono.error(new LRException("Class id not found"))))
        .then();
  }

  @Override
  public Mono<Clazz> findById(String id) {
    return cacheHelper.findCache(this.cacheKey(id), Clazz.class)
        .switchIfEmpty(Mono.defer(() -> clazzRepository.findByIdAndIsDeleted(id, ACTIVE)
            .switchIfEmpty(Mono.defer(() -> Mono.error(new LRException("Class id not found"))))));
  }

  @Override
  public Flux<Clazz> findAll() {
    return clazzRepository.findAllByIsDeleted(ACTIVE);
  }

  private String cacheKey(String id) {
    return Clazz.class.getName() + "-" + id;
  }

  private Clazz constructClazz(ClazzRequest clazzRequest) {
    return Clazz.builder()
        .code(clazzRequest.getCode())
        .name(clazzRequest.getName())
        .isDeleted(0)
        .createdDate(new Date())
        .build();
  }
}
