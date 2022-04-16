package com.javacode.springboot.rabbitmq.tutorial.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {


    public static final String MY_QUEUE = "my-queue";
    public static final String MY_TOPIC = "my-topic";
    public static final String MY_ROUTING_KEY = "my-routing-key";

    @Bean
    public Queue queue(){
        return new Queue(MY_QUEUE);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(MY_TOPIC) ;
    }

    @Bean
    public Binding bind(Queue queue,TopicExchange exchange){
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(MY_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate((connectionFactory));
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
