package com.example.demo.controller;

import com.example.demo.dto.OrderDto;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.Order;
import com.example.demo.repo.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = {"/orders"})
public class OrderController {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final OrderService orderService;

    public OrderController(OrderRepository orderRepository,
                           OrderMapper orderMapper,
                           OrderService orderService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.orderService = orderService;
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

    @EventListener
    public void handleApplicationContextInitializedEvent(ApplicationContextInitializedEvent event) {
    }

    @GetMapping("/allOrders")
    public PagedModel<Order> getAll(Pageable pageable) {
        Page<Order> orders = orderRepository.findAll(pageable);
        return new PagedModel<>(orders);
    }

    @PostMapping
    public Order create(@RequestBody @Valid Order order) {
        return orderService.create(order);
    }

    @PutMapping("/{id}")
    public OrderDto update(@PathVariable Long id, @RequestBody @Valid OrderDto dto) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));
        orderMapper.updateWithNull(dto, order);
        Order resultOrder = orderRepository.save(order);
        return orderMapper.toOrderDto(resultOrder);
    }
}

