package com.learn.reactive.repository;

import com.learn.reactive.model.Mahasiswa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;

@Repository
public interface MahasiswaRepository extends ReactiveMongoRepository<Mahasiswa, String> {
//public interface MahasiswaRepository extends MongoRepository<Mahasiswa, String> {
//    Page<Mahasiswa> findById(String nim, Pageable pageable);
}