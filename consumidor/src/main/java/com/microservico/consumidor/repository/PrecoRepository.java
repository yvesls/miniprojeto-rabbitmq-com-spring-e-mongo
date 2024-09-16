package com.microservico.consumidor.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.microservico.consumidor.model.Preco;

public interface PrecoRepository extends MongoRepository<Preco, String> {

    Optional<Preco> findByCodigoproduto(String codigo);
}
