package com.example.mongo_back.repository;

import com.example.mongo_back.domain.Venta;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VentaRepository extends MongoRepository<Venta, Long> {
}
