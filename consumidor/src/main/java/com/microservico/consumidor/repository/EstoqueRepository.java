package com.microservico.consumidor.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.microservico.consumidor.model.Estoque;

public interface EstoqueRepository extends MongoRepository<Estoque, String> {

    Optional<Estoque> findByCodigoproduto(String codigo);
}
