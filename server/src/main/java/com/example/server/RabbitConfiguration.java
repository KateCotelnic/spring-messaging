package com.example.server;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
    public static final String QUEUE = "kate-queue";
    public static final String QUEUE1 = "another-queue";
    public static final String EXCHANGE = "kate_exchange";
    public static final String ROUTING_KEY = "kate_routingKey";
    public static final String ROUTING_KEY1 = "another_routingKey";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }

    @Bean
    public Queue queue1() {
        return new Queue(QUEUE1);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(ROUTING_KEY);
    }

    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(queue1()).to(exchange()).with(ROUTING_KEY1);
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }
}
