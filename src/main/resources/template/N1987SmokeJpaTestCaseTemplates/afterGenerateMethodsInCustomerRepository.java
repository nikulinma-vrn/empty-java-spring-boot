package com.example.demo.repo;

import com.example.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Customer findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update Customer c set c.id = ?1, c.name = ?2")
    int updateIdAndNameBy(UUID id, String name);
}