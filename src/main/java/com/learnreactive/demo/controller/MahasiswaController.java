package com.learnreactive.demo.controller;

import com.learnreactive.demo.command.MahasiswaCommand;
import com.learnreactive.demo.model.Mahasiswa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/mahasiswa")
public class MahasiswaController {

    @Autowired
    MahasiswaCommand mahasiswaCommand;

    @GetMapping("/getAll")
    public Flux<Mahasiswa> getAll(){
        return mahasiswaCommand.findAllMahasiswa();
    }

    @PostMapping("/save")
    public Mono<Mahasiswa> saveMahasiswa(Mahasiswa mahasiswa){
        return mahasiswaCommand.saveMahasiswa(mahasiswa);
    }

    @GetMapping("/delete/{nim}")
    public Mono<Mahasiswa> deleteMahasiswa(@PathVariable  String nim){
        return mahasiswaCommand.deleteMahasiswa(nim);
    }

    @PutMapping("/update")
    public Mono<Mahasiswa> updateMahasiswa(Mahasiswa mahasiswa){
        return mahasiswaCommand.updateMahasiswa(mahasiswa);
    }

    @GetMapping("/get/{nim}")
    public Mono<Mahasiswa> getMahasiswa(@PathVariable  String nim){
        return mahasiswaCommand.getOneMahasiswa(nim);
    }
}