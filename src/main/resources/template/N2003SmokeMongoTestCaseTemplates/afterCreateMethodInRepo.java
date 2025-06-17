package com.example.demo.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LocalFileRepository extends MongoRepository<LocalFile, String> {
    List<LocalFile> findByName(String name);
}