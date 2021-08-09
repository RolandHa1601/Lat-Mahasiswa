package com.learn.reactive.service.impl;

import com.learn.reactive.exception.LRException;
import com.learn.reactive.model.Mahasiswa;
import com.learn.reactive.repository.MahasiswaRepository;
import com.learn.reactive.service.MahasiswaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.data.domain.Page;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import org.springframework.data.domain.Pageable;

@Service
@Slf4j
public class MahasiswaServiceImpl implements MahasiswaService {
    @Autowired
    MahasiswaRepository mahasiswaRepository;

    private final static String MHS = "mahahasiswa";

    @Autowired
    private ReactiveRedisTemplate<String, Mahasiswa> redisTemplate;
    public ReactiveValueOperations<String, Mahasiswa> reactiveValueOps;

    @Override
    public Mono<Mahasiswa> saveMahasiswa(Mahasiswa mahasiswa) {
      reactiveValueOps = redisTemplate.opsForValue();
        return Mono.just(mahasiswa)
            .flatMap(mhs -> mahasiswaRepository.save(mahasiswa))
            .flatMap(mahasiswa1 -> reactiveValueOps.set(MHS + "-" + mahasiswa.getNim(),
                mahasiswa))
            .map(s -> mahasiswa)
            .onErrorMap(er ->  {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR , "error saving",er);
            } );
    }

    @Override
    public Mono<Mahasiswa> updateMahasiswa(Mahasiswa mahasiswa) {
      reactiveValueOps = redisTemplate.opsForValue();
      return Mono.just(mahasiswa)
                    .flatMap(s -> mahasiswaRepository.findById(mahasiswa.getNim()))
                    .map(s -> {
                        s.setName(mahasiswa.getName());
                        s.setGPA(mahasiswa.getGPA());
                        return s;
                    })
                    .flatMap(s -> mahasiswaRepository.save(s))
                .flatMap(s-> reactiveValueOps.set(MHS + "-" + s.getNim() , s))
                .map(s -> mahasiswa)
                    .onErrorMap(er -> {
                        log.info("error nih");
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND , "not found mahasiswa",er);
                    });
//            return mahasiswaRepository.save(mahasiswa);
    }

    @Override
    public Flux<Mahasiswa> findAllMahasiswa() {
        return mahasiswaRepository.findAll();
    }

    @Override
    public Page<Mahasiswa> findAllMahasiswaWithPaging(Pageable paging) {
        return null;
    }

    @Override
    public Mono<Mahasiswa> getOneMahasiswa(String nim) {
      reactiveValueOps = redisTemplate.opsForValue();
      return Mono.just(nim)
            .flatMap(mhsNim -> reactiveValueOps.get(MHS + "-" + mhsNim))
//                .switchIfEmpty(s -> Mono.just(mahasiswaRepository.findById(nim)))
                .flatMap(s -> mahasiswaRepository.findById(nim))
                .onErrorMap(er -> {
                    log.info("error nih");
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND , "not found mahasiswa",er);
                });
    }

    @Override
    public Mono<String> deleteMahasiswa(String nim) {
      reactiveValueOps = redisTemplate.opsForValue();
      return Mono.just(nim)
          .flatMap(mhsNim -> reactiveValueOps.delete(MHS + "-" + mhsNim))
          .flatMap(s -> mahasiswaRepository.deleteById(nim))
          .map(a -> {
            return "ok";
          });
    }
}
