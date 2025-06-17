package com.example.demo.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocalFileRepository extends MongoRepository<LocalFile, String> {
}