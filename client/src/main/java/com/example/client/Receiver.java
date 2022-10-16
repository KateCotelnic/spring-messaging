package com.example.client;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @RabbitListener(queues = "kate-queue")
    public void consumeMessageFromQueue(String message) {
        System.out.println("Message received from queue : " + message);
    }
}
