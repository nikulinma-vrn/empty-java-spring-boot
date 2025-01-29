package com.example.demo.repository;

import com.example.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByNumber(Integer number);

    @Query("select count(o) from Order o where o.number = ?1")
    long countByNumber(Integer number);
}