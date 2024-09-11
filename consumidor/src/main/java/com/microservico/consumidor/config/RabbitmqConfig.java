package com.microservico.consumidor.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microservico.consumidor.exceptions.TratamentoErrorHandler;

@Configuration
public class RabbitmqConfig {

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitListenerContainerFactory<DirectMessageListenerContainer> rabbitListenerContainerFactory( ConnectionFactory connectionFactory ) {
        DirectRabbitListenerContainerFactory factory = new DirectRabbitListenerContainerFactory();

        factory.setConnectionFactory( connectionFactory );
        factory.setAcknowledgeMode( AcknowledgeMode.AUTO );
        factory.setGlobalQos( true );
        factory.setMessageConverter( jsonMessageConverter() );
        factory.setPrefetchCount( 4 );

        // factory.setGlobalQos(true);

        // Utilizando implementação da ErrorHandler
        factory.setErrorHandler( new TratamentoErrorHandler() );

        // Utilizando implementação da FatalStrategy
        // factory.setErrorHandler( errorHandler() );

        return factory;
    }
}
