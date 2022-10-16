package com.example.server;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/send")
public class Publisher {

    private final RabbitTemplate template;

    private static String defaultMessage = "Hi there! Here is a new message from publisher! Have a happy day!";

    @PostMapping()
    public String sendMessage(String message) {
        if(message!=null && !message.isBlank() && !message.isEmpty())
            template.convertAndSend(RabbitConfiguration.EXCHANGE, RabbitConfiguration.ROUTING_KEY, message);
        template.convertAndSend(RabbitConfiguration.EXCHANGE, RabbitConfiguration.ROUTING_KEY, defaultMessage);
        return "Successfully published!";
    }

    @PostMapping("/another")
    public String sendAnotherMessage(String message) {
        if(message!=null && !message.isBlank() && !message.isEmpty())
            template.convertAndSend(RabbitConfiguration.EXCHANGE, RabbitConfiguration.ROUTING_KEY1, message);
        template.convertAndSend(RabbitConfiguration.EXCHANGE, RabbitConfiguration.ROUTING_KEY1, "Message for another queue.");
        return "Successfully published!";
    }
}
