package com.example.demo.controller;

import com.example.demo.dto.OrderDto;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = {"/orders"})
public class OrderController1988 {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    public OrderController1988(OrderRepository orderRepository,
                               OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @GetMapping
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @GetMapping("/{number}")
    public List<OrderDto> findByNumber(@PathVariable Integer number) {
        List<Order> orders = orderRepository.findByNumber(number);
        return orders.stream()
                .map(orderMapper::toOrderDto)
                .toList();
    }

    @EventListener
    public void handleApplicationContextInitializedEvent(ApplicationContextInitializedEvent event) {
    }

    @EventListener
    public void handleApplicationFailedEvent(ApplicationFailedEvent event) {
    }
}

