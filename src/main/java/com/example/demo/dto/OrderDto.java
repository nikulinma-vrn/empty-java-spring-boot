package com.example.demo.dto;

import com.example.demo.model.Order;
import jakarta.validation.constraints.PastOrPresent;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO for {@link Order}
 */
public class OrderDto {
    private final Long id;
    private final Integer number;
    private final BigDecimal amount;
    @PastOrPresent
    private final LocalDate orderDate;
    private final CustomerDto customer;

    public OrderDto(Long id, Integer number, BigDecimal amount, LocalDate orderDate, CustomerDto customer) {
        this.id = id;
        this.number = number;
        this.amount = amount;
        this.orderDate = orderDate;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto entity = (OrderDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.number, entity.number) &&
                Objects.equals(this.amount, entity.amount) &&
                Objects.equals(this.orderDate, entity.orderDate) &&
                Objects.equals(this.customer, entity.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, amount, orderDate, customer);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "number = " + number + ", " +
                "amount = " + amount + ", " +
                "orderDate = " + orderDate + ", " +
                "customer = " + customer + ")";
    }
}