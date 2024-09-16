package com.microservico.consumidor.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.microservico.constants.RabbitMQConstants;
import com.microservico.consumidor.service.PrecoService;
import com.microservico.dto.PrecoDTO;

@Component
public class PrecoConsumer {

    @Autowired
    private PrecoService precoService;

    @RabbitListener(queues = RabbitMQConstants.FILA_PRECO)
    private void consumidor(PrecoDTO precoDTO) {
        precoService.insert(precoDTO);
    }
}
