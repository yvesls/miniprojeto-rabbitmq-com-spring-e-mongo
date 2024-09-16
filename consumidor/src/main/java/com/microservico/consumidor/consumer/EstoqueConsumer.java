package com.microservico.consumidor.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.microservico.dto.EstoqueDTO;
import com.microservico.constants.RabbitMQConstants;
import com.microservico.consumidor.service.EstoqueService;

@Component
public class EstoqueConsumer {

    @Autowired
    private EstoqueService estoqueService;

    @RabbitListener(queues = RabbitMQConstants.FILA_ESTOQUE)
    private void consumidor(EstoqueDTO estoqueDTO) {
        estoqueService.insert(estoqueDTO);
    }
}
