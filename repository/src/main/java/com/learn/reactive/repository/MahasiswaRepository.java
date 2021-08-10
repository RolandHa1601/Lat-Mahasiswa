package com.learn.reactive.repository;

import com.learn.reactive.model.Mahasiswa;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MahasiswaRepository extends ReactiveMongoRepository<Mahasiswa, String> {
}