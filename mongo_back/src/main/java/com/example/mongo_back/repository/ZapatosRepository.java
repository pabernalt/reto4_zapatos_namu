package com.example.mongo_back.repository;

import com.example.mongo_back.domain.Zapatos;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ZapatosRepository extends MongoRepository<Zapatos, Long> {
}
