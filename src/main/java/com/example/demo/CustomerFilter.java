package com.example.demo;

import com.example.demo.entity.Customer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public record CustomerFilter(Integer number, String name) {
    public Specification<Customer> toSpecification() {
        return Specification.where(numberSpec())
                .and(nameSpec());
    }

    private Specification<Customer> numberSpec() {
        return ((root, query, cb) -> number != null
                ? cb.equal(root.get("number"), number)
                : null);
    }

    private Specification<Customer> nameSpec() {
        return ((root, query, cb) -> StringUtils.hasText(name)
                ? cb.equal(root.get("name"), name)
                : null);
    }
}