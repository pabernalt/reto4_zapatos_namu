package com.example.mongo_back.repository;

import com.example.mongo_back.domain.Counter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CounterRepository extends MongoRepository<Counter, String> {
    Optional<Counter> findTopByOrderByIdDesc();
}
