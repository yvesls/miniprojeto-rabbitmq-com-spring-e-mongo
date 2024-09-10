package com.microservico.mini_projeto_rabbitmq_spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.microservico.dto.PrecoDTO;
import com.microservico.constants.RabbitMQConstants;
import com.microservico.mini_projeto_rabbitmq_spring_boot.service.RabbitMQService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping( value = "preco" )
public class PrecoController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PutMapping
    private ResponseEntity alteraPreco( @RequestBody PrecoDTO precoDTO ) {
        this.rabbitMQService.enviaMensagem( RabbitMQConstants.FILA_PRECO, precoDTO );
        return new ResponseEntity<>( HttpStatus.OK );
    }
}
