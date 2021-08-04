package com.learnreactive.demo.repository;

import com.learnreactive.demo.model.Mahasiswa;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MahasiswaRepository extends ReactiveMongoRepository<Mahasiswa, String> {

}
