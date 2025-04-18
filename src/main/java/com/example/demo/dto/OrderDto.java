package com.example.demo.dto;

import com.example.demo.entity.Order;
import jakarta.validation.constraints.PastOrPresent;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO for {@link Order}
 */
public class OrderDto {
    private final Long id;
    private final String number;
    private final BigDecimal amount;
    @PastOrPresent
    private final LocalDate orderDate;
    private final CutomerDto cutomer;

    public OrderDto(Long id, String number, BigDecimal amount, LocalDate orderDate, CutomerDto cutomer) {
        this.id = id;
        this.number = number;
        this.amount = amount;
        this.orderDate = orderDate;
        this.cutomer = cutomer;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public CutomerDto getCutomer() {
        return cutomer;
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
                Objects.equals(this.cutomer, entity.cutomer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, amount, orderDate, cutomer);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "number = " + number + ", " +
                "amount = " + amount + ", " +
                "orderDate = " + orderDate + ", " +
                "cutomer = " + cutomer + ")";
    }
}