package com.microservico.mini_projeto_rabbitmq_spring_boot.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Jackson2JsonMessageConverter jsonMessageConverter;

    public void enviaMensagem( String nomeFila, Object mensagem ) {
        rabbitTemplate.setMessageConverter( jsonMessageConverter );
        rabbitTemplate.convertAndSend( nomeFila, mensagem );
    }
}
