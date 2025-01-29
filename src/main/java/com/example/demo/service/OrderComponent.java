package com.example.demo.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderComponent {
    @KafkaListener(topics = "newTopic", containerFactory = "stringListenerFactory")
    public void consumeString(String string) {
        
    }
}
