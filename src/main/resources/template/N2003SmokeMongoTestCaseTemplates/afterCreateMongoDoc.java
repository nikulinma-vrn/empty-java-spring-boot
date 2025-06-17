package com.example.demo.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class LocalFile {

    @Id
    private String id;

    @Field("name")
    private String name;

    @Transient
    private String isActual;

    public String getIsActual() {
        return isActual;
    }

    public void setIsActual(String isActual) {
        this.isActual = isActual;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}