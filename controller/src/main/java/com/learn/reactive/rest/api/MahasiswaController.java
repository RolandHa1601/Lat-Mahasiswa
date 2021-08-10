package com.learn.reactive.rest.api;

import com.learn.reactive.model.Kelas;
import com.learn.reactive.model.Mahasiswa;
import com.learn.reactive.service.MahasiswaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/mahasiswa")
public class MahasiswaController {

    @Autowired
    MahasiswaService mahasiswaService;


    @GetMapping("/getAll")
    public Flux<Mahasiswa> getAll(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size){
        Pageable paging = PageRequest.of(page, size);
        return mahasiswaService.findAllMahasiswa();
    }

    @GetMapping("/getAllMhs")
    public Page<Mahasiswa> getAllMhs(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size){
        Pageable paging = PageRequest.of(page, size);
        return mahasiswaService.findAllMahasiswaWithPaging(paging);
    }


    @PostMapping("/save")
    public Mono<Mahasiswa> saveMahasiswa(Mahasiswa mahasiswa){
        return mahasiswaService.saveMahasiswa(mahasiswa);
    }

    @PostMapping("/save-kelas")
    public Mono<Kelas> saveKelas(Kelas kelas){
        return Mono.just(kelas);
    }

    @PostMapping("/get-kelas")
    public Mono<Kelas> getKelas(){
        Kelas kelas = Kelas.builder().kodeKelas("a01").namaDosen("rnd").namaKelas("a01").build();
        return Mono.just(kelas);
    }


    @DeleteMapping("/delete/{nim}")
    public Mono<String> deleteMahasiswa(@PathVariable String nim){
        return mahasiswaService.deleteMahasiswa(nim);
    }

    @PutMapping("/update")
    public Mono<Mahasiswa> updateMahasiswa(Mahasiswa mahasiswa){
        return mahasiswaService.updateMahasiswa(mahasiswa);
    }

    @GetMapping("/get/{nim}")
    public Mono<Mahasiswa> getMahasiswa(@PathVariable  String nim){
//        reactiveValueOps = redisTemplate.opsForValue();
//        return Mono.just(nim)
//            .flatMap(s -> reactiveValueOps.get(s));
        return mahasiswaService.getOneMahasiswa(nim);
    }
}
