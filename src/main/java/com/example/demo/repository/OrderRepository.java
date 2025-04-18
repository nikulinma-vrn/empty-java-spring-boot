package com.example.demo.repository;

import com.example.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByNumber(String number);

    long countByCutomer_Name(String name);
}