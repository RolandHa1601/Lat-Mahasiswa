package com.learn.reactive.app;

import com.learn.reactive.model.Kelas;
import com.learn.reactive.model.Mahasiswa;
import com.learn.reactive.service.MahasiswaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TestController {

    @GetMapping("/getTest")
    public Flux<Integer> getAllMhs(){
        return Flux.just(1,2,3,4).log();
    }
}
