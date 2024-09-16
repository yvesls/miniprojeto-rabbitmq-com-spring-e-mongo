package com.microservico.mini_projeto_rabbitmq_spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservico.constants.RabbitMQConstants;
import com.microservico.dto.EstoqueDTO;
import com.microservico.mini_projeto_rabbitmq_spring_boot.service.RabbitMQService;

@RestController
@RequestMapping(value = "estoque")
public class EstoqueController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PutMapping
    private ResponseEntity<EstoqueDTO> alteraEstoque(@RequestBody EstoqueDTO estoqueDTO) {
        this.rabbitMQService.enviaMensagem(RabbitMQConstants.FILA_ESTOQUE, estoqueDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
