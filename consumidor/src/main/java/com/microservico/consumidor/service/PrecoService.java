package com.microservico.consumidor.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.microservico.consumidor.model.Preco;
import com.microservico.consumidor.repository.PrecoRepository;
import com.microservico.dto.PrecoDTO;
import com.mongodb.DuplicateKeyException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrecoService {

    private final PrecoRepository precoRepository;

    public PrecoDTO insert(PrecoDTO precoDTO) {
        try {
            var preco = precoRepository.save(new Preco(precoDTO.getCodigoproduto(), precoDTO.getValor()));
            return new PrecoDTO(preco.getCodigoproduto(), preco.getValor());
        } catch (DuplicateKeyException e) {
            throw new IllegalArgumentException("Código do produto já existe: " + precoDTO.getCodigoproduto());
        }
    }

    public List<PrecoDTO> findAll() {
        var precos = precoRepository.findAll();
        return precos.stream()
                .map(preco -> new PrecoDTO(preco.getCodigoproduto(), preco.getValor()))
                .collect(Collectors.toList());
    }

    public PrecoDTO findByCodigo(String codigo) {
        var optionalPreco = precoRepository.findByCodigoproduto(codigo);

        return optionalPreco
                .map(preco -> new PrecoDTO(preco.getCodigoproduto(), preco.getValor()))
                .orElse(null);
    }

    public void deleteByCodigoproduto(String codigo) {
        var precoDTO = findByCodigo(codigo);
        precoRepository.delete(new Preco(precoDTO.getCodigoproduto(), precoDTO.getValor()));
    }

    public PrecoDTO update(String codigo, PrecoDTO precoDTO) {
        try {
            var precoBanco = findByCodigo(codigo);
            if (precoDTO != null) {
                BeanUtils.copyProperties(precoDTO, precoBanco);
                precoRepository.save(new Preco(precoDTO.getCodigoproduto(), precoDTO.getValor()));
            }

            return precoBanco;
        } catch (DuplicateKeyException e) {
            throw new IllegalArgumentException("Código do produto já existe: " + codigo);
        }
    }
}
