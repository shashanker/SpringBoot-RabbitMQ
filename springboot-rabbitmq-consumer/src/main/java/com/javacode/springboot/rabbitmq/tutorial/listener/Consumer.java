package com.javacode.springboot.rabbitmq.tutorial.listener;

import com.javacode.springboot.rabbitmq.tutorial.config.RabbitMQConfig;
import com.javacode.springboot.rabbitmq.tutorial.dto.CustomMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
public class Consumer {

    @RabbitListener(queues = RabbitMQConfig.MY_QUEUE)
    public void listener(CustomMessage message){
        System.out.println(message);
    }
}
