package com.example.mongo_back.repository;

import com.example.mongo_back.domain.Usuarios;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuarios, Long> {
    Optional<Usuarios> findUsuariosByUsername(String username);
}
