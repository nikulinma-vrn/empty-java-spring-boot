package com.example.demo.service;

import com.example.demo.CutomerFilter;
import com.example.demo.entity.Cutomer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface CutomerService {
    Page<Cutomer> getAll(CutomerFilter filter, Pageable pageable);

    Cutomer getOne(UUID id);

    List<Cutomer> getMany(List<UUID> ids);

    Cutomer create(Cutomer cutomer);

    Cutomer patch(UUID id, JsonNode patchNode) throws IOException;

    List<UUID> patchMany(List<UUID> ids, JsonNode patchNode) throws IOException;

    Cutomer delete(UUID id);

    void deleteMany(List<UUID> ids);
}
