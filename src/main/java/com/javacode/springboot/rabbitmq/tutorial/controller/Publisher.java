package com.javacode.springboot.rabbitmq.tutorial.controller;


import com.javacode.springboot.rabbitmq.tutorial.config.RabbitMQConfig;
import com.javacode.springboot.rabbitmq.tutorial.dto.CustomMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
public class Publisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/publish")
    public String publishMessage(@RequestBody CustomMessage message){
        message.setId(UUID.randomUUID().toString());
        message.setMsgDate(new Date());
        template.convertAndSend(RabbitMQConfig.MY_TOPIC,
                RabbitMQConfig.MY_ROUTING_KEY,message);
        return "Message Published";
    }
}
