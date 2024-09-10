package com.microservico.consumidor.consumer;

import com.microservico.dto.PrecoDTO;
import com.microservico.constants.RabbitMQConstants;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PrecoConsumer {

    @RabbitListener( queues = RabbitMQConstants.FILA_PRECO )
    private void consumidor( PrecoDTO precoDTO ) {
        System.out.println( precoDTO.codigoproduto );
        System.out.println( precoDTO.preco );
    }
}
