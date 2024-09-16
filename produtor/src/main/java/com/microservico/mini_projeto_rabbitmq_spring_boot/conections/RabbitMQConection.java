package com.microservico.mini_projeto_rabbitmq_spring_boot.conections;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import com.microservico.constants.RabbitMQConstants;

import jakarta.annotation.PostConstruct;

@Component
public class RabbitMQConection {

    private static final String NOME_EXCHANGE = "amq.direct";
    private AmqpAdmin ampqAdmin;

    public RabbitMQConection( AmqpAdmin amqpAdmin ) {
        this.ampqAdmin = amqpAdmin;
    }

    private Queue fila( String nomeFila ) {
        return new Queue( nomeFila, true, false, false );
    }

    private DirectExchange trocaDireta() {
        return new DirectExchange( NOME_EXCHANGE );
    }

    private Binding relacionamento( Queue fila, DirectExchange troca ) {
        return new Binding( fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null );
    }

    @PostConstruct
    private void adiciona() {
        Queue filaEstoque = this.fila( RabbitMQConstants.FILA_ESTOQUE );
        Queue filaPreco = this.fila( RabbitMQConstants.FILA_PRECO );

        DirectExchange troca = this.trocaDireta();

        Binding ligacaoEstoque = this.relacionamento( filaEstoque, troca );
        Binding ligacaoPreco = this.relacionamento( filaPreco, troca );

        this.ampqAdmin.declareQueue( filaEstoque );
        this.ampqAdmin.declareQueue( filaPreco );

        this.ampqAdmin.declareExchange( troca );

        this.ampqAdmin.declareBinding( ligacaoEstoque );
        this.ampqAdmin.declareBinding( ligacaoPreco );
    }
}
