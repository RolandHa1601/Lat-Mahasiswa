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
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Service
@Slf4j
public class MahasiswaServiceImpl implements MahasiswaService {
    @Autowired
    MahasiswaRepository mahasiswaRepository;

    @Override
    public Mono<Mahasiswa> saveMahasiswa(Mahasiswa mahasiswa) {
        return mahasiswaRepository.save(mahasiswa);
    }

    @Override
    public Mono<Mahasiswa> updateMahasiswa(Mahasiswa mahasiswa) {
            Mahasiswa ssss = new Mahasiswa();
            ssss.setGPA(3.1);
            ssss.setNim("19");
            ssss.setName("Rlnd");
            return Mono.just(mahasiswa)
                    .flatMap(s -> mahasiswaRepository.findById(mahasiswa.getNim()))
                    .map(s -> {
                        log.info(s.getName());
                        s.setName(mahasiswa.getName());
                        s.setGPA(mahasiswa.getGPA());
                        return s;
                    })
                    .flatMap(s -> mahasiswaRepository.save(s))
                    .map(s -> {
                        log.info("test");
                        return s;
                    })
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
    public Mono<Mahasiswa> getOneMahasiswa(String nim) {
        return mahasiswaRepository.findById(nim);
    }

    @Override
    public Mono<Void> deleteMahasiswa(String nim) {
        return mahasiswaRepository.deleteById(nim);
    }
}
