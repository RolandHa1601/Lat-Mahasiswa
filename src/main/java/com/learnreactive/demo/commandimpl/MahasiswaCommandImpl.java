package com.learnreactive.demo.commandimpl;

import com.learnreactive.demo.command.MahasiswaCommand;
import com.learnreactive.demo.model.Mahasiswa;
import com.learnreactive.demo.repository.MahasiswaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

@Service
@Slf4j
public class MahasiswaCommandImpl implements MahasiswaCommand {
//    @Autowired
//    MahasiswaRepository mahasiswaRepository;

    Map<String,Mahasiswa> mhsMap =  new HashMap<>();

    @Override
    public Mono<Mahasiswa> saveMahasiswa(Mahasiswa mahasiswa) {
        return Mono.just(mahasiswa).
                map(mahasiswa1 -> {
                    mhsMap.put(mahasiswa.getNim(),mahasiswa);
                    return mahasiswa1;
                });
    }

    @Override
    public Mono<Mahasiswa> updateMahasiswa(Mahasiswa mahasiswa) {
        return Mono.just(mahasiswa).
                map(mahasiswa1 -> {
                    mhsMap.put(mahasiswa.getNim(),mahasiswa);
                    return mahasiswa1;
                });
    }

    @Override
    public Flux<Mahasiswa> findAllMahasiswa() {
        return Flux.fromIterable(mhsMap.values());
    }

    @Override
    public Mono<Mahasiswa> getOneMahasiswa(String nim) {
        return Mono.just(mhsMap.get(nim));
    }

    @Override
    public Mono<Mahasiswa> deleteMahasiswa(String nim) {
        return Mono.just(mhsMap.remove(nim));
    }
}
