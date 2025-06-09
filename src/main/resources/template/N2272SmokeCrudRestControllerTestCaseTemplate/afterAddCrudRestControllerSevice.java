package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface CustomerService {
    Page<Customer> getAll(CustomerFilter filter, Pageable pageable);

    Customer getOne(UUID id);

    List<Customer> getMany(List<UUID> ids);

    Customer create(Customer customer);

    Customer patch(UUID id, JsonNode patchNode) throws IOException;

    List<UUID> patchMany(List<UUID> ids, JsonNode patchNode) throws IOException;

    Customer delete(UUID id);

    void deleteMany(List<UUID> ids);
}
