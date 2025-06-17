package com.example.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderComponent {
    @KafkaListener(topics = "orderCreated", containerFactory = "stringListenerFactory")
    public void consumeString(String string) {

    }
}