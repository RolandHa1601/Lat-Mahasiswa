package com.learn.reactive.repository;

import com.learn.reactive.model.Mahasiswa;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface MahasiswaRepository extends ReactiveMongoRepository<Mahasiswa, String> {

}