package com.learn.reactive.app;

import com.learn.reactive.model.Kelas;
import com.learn.reactive.model.Mahasiswa;
import com.learn.reactive.service.MahasiswaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/mahasiswa")
public class MahasiswaController {

    @Autowired
    MahasiswaService mahasiswaService;

    @GetMapping("/getAll")
    public Flux<Mahasiswa> getAll(){
        System.out.println("testt");
        return mahasiswaService.findAllMahasiswa();
    }

    @PostMapping("/save")
    public Mono<Mahasiswa> saveMahasiswa(Mahasiswa mahasiswa){
        return mahasiswaService.saveMahasiswa(mahasiswa);
    }

    @PostMapping("/save-kelas")
    public Mono<Kelas> saveKelas(Kelas kelas){
        return Mono.just(kelas);
    }


    @GetMapping("/delete/{nim}")
    public Mono<String> deleteMahasiswa(@PathVariable String nim){
        return mahasiswaService.deleteMahasiswa(nim).map(
                e-> {
                    return "ok";
                }
        );
    }

    @PutMapping("/update")
    public Mono<Mahasiswa> updateMahasiswa(Mahasiswa mahasiswa){
        return mahasiswaService.updateMahasiswa(mahasiswa);
    }

    @GetMapping("/get/{nim}")
    public Mono<Mahasiswa> getMahasiswa(@PathVariable  String nim){
        return mahasiswaService.getOneMahasiswa(nim);
    }
}
