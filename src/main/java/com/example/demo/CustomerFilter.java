package com.example.demo;

import com.example.demo.model.Customer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public record CustomerFilter(String name, Integer number) {
    public Specification<Customer> toSpecification() {
        return Specification.where(nameSpec())
                .and(numberSpec());
    }

    private Specification<Customer> nameSpec() {
        return ((root, query, cb) -> StringUtils.hasText(name)
                ? cb.equal(root.get("name"), name)
                : null);
    }

    private Specification<Customer> numberSpec() {
        return ((root, query, cb) -> number != null
                ? cb.equal(root.get("number"), number)
                : null);
    }
}