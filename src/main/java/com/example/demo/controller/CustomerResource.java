package com.example.demo.controller;

import com.example.demo.CustomerFilter;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/admin-ui/customers")
public class CustomerResource {

    private final CustomerService customerService;

    private final CustomerRepository customerRepository;

    public CustomerResource(CustomerService customerService,
                            CustomerRepository customerRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public PagedModel<Customer> getAll(@ModelAttribute CustomerFilter filter, Pageable pageable) {
        Page<Customer> customers = customerService.getAll(filter, pageable);
        return new PagedModel<>(customers);
    }

    @GetMapping("/{id}")
    public Customer getOne(@PathVariable UUID id) {
        return customerService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<Customer> getMany(@RequestParam List<UUID> ids) {
        return customerService.getMany(ids);
    }

    @PostMapping
    public Customer create(@RequestBody @Valid Customer customer) {
        return customerService.create(customer);
    }

    @PatchMapping("/{id}")
    public Customer patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) throws IOException {
        return customerService.patch(id, patchNode);
    }

    @PatchMapping
    public List<UUID> patchMany(@RequestParam @Valid List<UUID> ids, @RequestBody JsonNode patchNode) throws IOException {
        return customerService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    public Customer delete(@PathVariable UUID id) {
        return customerService.delete(id);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<UUID> ids) {
        customerService.deleteMany(ids);
    }

    @GetMapping("/allCustomers")
    public PagedModel<Customer> getAll1(Pageable pageable) {
        Page<Customer> customers = customerRepository.findAll(pageable);
        return new PagedModel<>(customers);
    }
}
