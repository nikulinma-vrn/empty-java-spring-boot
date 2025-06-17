package com.example.demo.controller;

import com.example.demo.dto.OrderDto;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.Order;
import com.example.demo.repo.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    public OrderController(OrderRepository orderRepository,
                           OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @DeleteMapping
    public void deleteAll() {
        orderRepository.deleteAll();
    }

    @GetMapping("/{number}")
    public ResponseEntity<List<OrderDto>> findByNumber(@PathVariable Integer number) {
        List<Order> orders = orderRepository.findByNumber(number);
        List<OrderDto> orderDtos = orders.stream()
                .map(orderMapper::toOrderDto)
                .toList();
        return ResponseEntity.ok(orderDtos);
    }
}

