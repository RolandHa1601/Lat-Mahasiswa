package com.learn.reactive.service;

import com.learn.reactive.model.Mahasiswa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MahasiswaService {

    Mono<Mahasiswa> saveMahasiswa(Mahasiswa mahasiswa);

    Mono<Mahasiswa> updateMahasiswa(Mahasiswa mahasiswa);

    Flux<Mahasiswa> findAllMahasiswa( );

    Page<Mahasiswa> findAllMahasiswaWithPaging(Pageable paging);

    Mono<Mahasiswa> getOneMahasiswa(String nim);

    Mono<String> deleteMahasiswa(String nim);
}