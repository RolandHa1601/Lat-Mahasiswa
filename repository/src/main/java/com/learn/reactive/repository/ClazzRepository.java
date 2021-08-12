package com.learn.reactive.repository;

import com.learn.reactive.model.dao.Clazz;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ClazzRepository extends ReactiveMongoRepository<Clazz, String> {

  Flux<Clazz> findAllByIsDeleted(int isDeleted);

  Mono<Clazz> findByIdAndIsDeleted(String id, int isDeleted);
}
