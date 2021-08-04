package com.learnreactive.demo.command;

import com.learnreactive.demo.model.Mahasiswa;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MahasiswaCommand {

    public Mono<Mahasiswa> saveMahasiswa(Mahasiswa mahasiswa);

    public Mono<Mahasiswa> updateMahasiswa(Mahasiswa mahasiswa);

    public Flux<Mahasiswa> findAllMahasiswa();

    public Mono<Mahasiswa> getOneMahasiswa(String nim);

    public Mono<Void> deleteMahasiswa(String nim);
}
