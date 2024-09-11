package com.microservico.consumidor.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.microservico.dto.EstoqueDTO;
import com.microservico.constants.RabbitMQConstants;

@Component
public class EstoqueConsumer {

    @RabbitListener( queues = RabbitMQConstants.FILA_ESTOQUE )
    private void consumidor( EstoqueDTO estoqueDTO ) {
        System.out.println( estoqueDTO.getCodigoproduto() );
        System.out.println( estoqueDTO.getQuantidade() );
    }
}
