package com.example.demo.dto;

import com.example.demo.entity.Cutomer;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Objects;
import java.util.UUID;

/**
 * DTO for {@link Cutomer}
 */
public class CutomerDto {
    private final UUID id;
    private final String name;
    private final String email;
    @PositiveOrZero
    private final Integer number;

    public CutomerDto(UUID id, String name, String email, Integer number) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.number = number;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CutomerDto entity = (CutomerDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.number, entity.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, number);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "email = " + email + ", " +
                "number = " + number + ")";
    }
}