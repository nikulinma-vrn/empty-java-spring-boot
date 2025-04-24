package com.example.demo;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final ObjectMapper objectMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                              ObjectMapper objectMapper) {
        this.customerRepository = customerRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Page<Customer> getAll(CustomerFilter filter, Pageable pageable) {
        Specification<Customer> spec = filter.toSpecification();
        return customerRepository.findAll(spec, pageable);
    }

    @Override
    public Customer getOne(UUID id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        return customerOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));
    }

    @Override
    public List<Customer> getMany(List<UUID> ids) {
        return customerRepository.findAllById(ids);
    }

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer patch(UUID id, JsonNode patchNode) throws IOException {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        objectMapper.readerForUpdating(customer).readValue(patchNode);

        return customerRepository.save(customer);
    }

    @Override
    public List<UUID> patchMany(List<UUID> ids, JsonNode patchNode) throws IOException {
        Collection<Customer> customers = customerRepository.findAllById(ids);

        for (Customer customer : customers) {
            objectMapper.readerForUpdating(customer).readValue(patchNode);
        }

        List<Customer> resultCustomers = customerRepository.saveAll(customers);
        return resultCustomers.stream()
                .map(Customer::getId)
                .toList();
    }

    @Override
    public Customer delete(UUID id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customerRepository.delete(customer);
        }
        return customer;
    }

    @Override
    public void deleteMany(List<UUID> ids) {
        customerRepository.deleteAllById(ids);
    }
}
