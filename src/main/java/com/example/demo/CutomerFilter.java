package com.example.demo;

import com.example.demo.entity.Cutomer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public record CutomerFilter(Integer number, String name) {
    public Specification<Cutomer> toSpecification() {
        return Specification.where(numberSpec())
                .and(nameSpec());
    }

    private Specification<Cutomer> numberSpec() {
        return ((root, query, cb) -> number != null
                ? cb.equal(root.get("number"), number)
                : null);
    }

    private Specification<Cutomer> nameSpec() {
        return ((root, query, cb) -> StringUtils.hasText(name)
                ? cb.equal(root.get("name"), name)
                : null);
    }
}