package com.microservico.consumidor.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.microservico.consumidor.model.Estoque;
import com.microservico.consumidor.repository.EstoqueRepository;
import com.microservico.dto.EstoqueDTO;
import com.mongodb.DuplicateKeyException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;

    public List<EstoqueDTO> findAll() {
        var estoques = estoqueRepository.findAll();
        return estoques.stream()
                .map(estoque -> new EstoqueDTO(estoque.getCodigoproduto(), estoque.getQuantidade()))
                .collect(Collectors.toList());
    }

    public EstoqueDTO findByCodigo(String codigo) {
        var optionalEstoque = estoqueRepository.findByCodigoproduto(codigo);

        return optionalEstoque
                .map(estoque -> new EstoqueDTO(estoque.getCodigoproduto(), estoque.getQuantidade()))
                .orElse(null);
    }

    public void deleteByCodigoproduto(String codigo) {
        var estoqueDTO = findByCodigo(codigo);
        estoqueRepository.delete(new Estoque(estoqueDTO.getCodigoproduto(), estoqueDTO.getQuantidade()));
    }

    public EstoqueDTO insert(EstoqueDTO estoqueDTO) {
        try {
            findByCodigo(estoqueDTO.getCodigoproduto());
            var estoque = estoqueRepository
                    .save(new Estoque(estoqueDTO.getCodigoproduto(), estoqueDTO.getQuantidade()));
            return new EstoqueDTO(estoque.getCodigoproduto(), estoque.getQuantidade());
        } catch (DuplicateKeyException e) {
            throw new IllegalArgumentException("Código do produto já existe: " + estoqueDTO.getCodigoproduto());
        }
    }

    public EstoqueDTO update(String codigo, EstoqueDTO estoqueDTO) {
        try {
            var estoqueBanco = findByCodigo(codigo);

            if (estoqueDTO != null) {
                BeanUtils.copyProperties(estoqueDTO, estoqueBanco);
                estoqueRepository.save(new Estoque(estoqueDTO.getCodigoproduto(), estoqueDTO.getQuantidade()));
            }
            return estoqueBanco;
        } catch (DuplicateKeyException e) {
            throw new IllegalArgumentException("Código do produto já existe: " + codigo);
        }
    }
}
