package com.learn.reactive.service;

import com.learn.reactive.model.Mahasiswa;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MahasiswaService {

    Mono<Mahasiswa> saveMahasiswa(Mahasiswa mahasiswa);

    Mono<Mahasiswa> updateMahasiswa(Mahasiswa mahasiswa);

    Flux<Mahasiswa> findAllMahasiswa();

    Mono<Mahasiswa> getOneMahasiswa(String nim);

    Mono<Void> deleteMahasiswa(String nim);
}