package com.learn.reactive.service.impl;

//@Service
//@Slf4j
//public class MahasiswaCommandImpl implements MahasiswaCommand {
////    @Autowired
////    MahasiswaRepository mahasiswaRepository;
//
//    Map<String,Mahasiswa> mhsMap =  new HashMap<>();
//
//    @Override
//    public Mono<Mahasiswa> saveMahasiswa(Mahasiswa mahasiswa) {
//        return Mono.just(mahasiswa).
//                map(mahasiswa1 -> {
//                    mhsMap.put(mahasiswa.getNim(),mahasiswa);
//                    return mahasiswa1;
//                });
//    }
//
//    @Override
//    public Mono<Mahasiswa> updateMahasiswa(Mahasiswa mahasiswa) {
//        return Mono.just(mahasiswa).
//                map(mahasiswa1 -> {
//                    mhsMap.put(mahasiswa.getNim(),mahasiswa);
//                    return mahasiswa1;
//                });
//    }
//
//    @Override
//    public Flux<Mahasiswa> findAllMahasiswa() {
//        return Flux.fromIterable(mhsMap.values());
//    }
//
//    @Override
//    public Mono<Mahasiswa> getOneMahasiswa(String nim) {
//        return Mono.just(mhsMap.get(nim));
//    }
//
//    @Override
//    public Mono<Mahasiswa> deleteMahasiswa(String nim) {
//        return Mono.just(mhsMap.remove(nim));
//    }
//}


import com.learn.reactive.exception.LRException;
import com.learn.reactive.model.Mahasiswa;
import com.learn.reactive.repository.MahasiswaRepository;
import com.learn.reactive.service.MahasiswaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public Mono<Mahasiswa> saveMahasiswa(Mahasiswa mahasiswa) {
//        return null;
        return Mono.just(mahasiswaRepository.save(mahasiswa));
    }

    @Override
    public Mono<Mahasiswa> updateMahasiswa(Mahasiswa mahasiswa) {
            Mahasiswa ssss = new Mahasiswa();
            ssss.setGPA(3.1);
            ssss.setNim("19");
            ssss.setName("Rlnd");
            return Mono.just(mahasiswa)
                    .map(s -> mahasiswaRepository.findById(mahasiswa.getNim()))
                    .map(s -> s.get())
                    .map(s -> {
                        log.info(s.getNim());
                        s.setName(mahasiswa.getName());
                        s.setGPA(mahasiswa.getGPA());
                        return s;
                    })
                    .map(s -> mahasiswaRepository.save(s))
                    .onErrorMap(er -> {
                        log.info("error nih");
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND , "not found mahasiswa",er);
                    });
//            return mahasiswaRepository.save(mahasiswa);
    }

    @Override
    public Flux<Mahasiswa> findAllMahasiswa() {
        return Flux.empty();
    }

    @Override
    public Page<Mahasiswa> findAllMahasiswaWithPaging(Pageable paging) {
        return mahasiswaRepository.findAll(paging);
    }

    @Override
    public Mono<Mahasiswa> getOneMahasiswa(String nim) {
        return Mono.just(nim)
                .map(s -> mahasiswaRepository.findById(s))
                .map(s -> s.get())
                .onErrorMap(er -> {
                    log.info("error nih");
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND , "not found mahasiswa",er);
                });
    }

    @Override
    public Mono<String> deleteMahasiswa(String nim) {
        mahasiswaRepository.deleteById(nim);
        return Mono.just(nim)
                .map(a -> {
                    return "ok";
                });
    }
}
