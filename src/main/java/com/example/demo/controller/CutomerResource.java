package com.example.demo.controller;

import com.example.demo.CutomerFilter;
import com.example.demo.service.CutomerService;
import com.example.demo.entity.Cutomer;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/admin-ui/cutomers")
public class CutomerResource {

    private final CutomerService cutomerService;

    public CutomerResource(CutomerService cutomerService) {
        this.cutomerService = cutomerService;
    }

    @GetMapping
    public PagedModel<Cutomer> getAll(@ModelAttribute CutomerFilter filter, Pageable pageable) {
        Page<Cutomer> cutomers = cutomerService.getAll(filter, pageable);
        return new PagedModel<>(cutomers);
    }

    @GetMapping("/{id}")
    public Cutomer getOne(@PathVariable UUID id) {
        return cutomerService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<Cutomer> getMany(@RequestParam List<UUID> ids) {
        return cutomerService.getMany(ids);
    }

    @PostMapping
    public Cutomer create(@RequestBody @Valid Cutomer cutomer) {
        return cutomerService.create(cutomer);
    }

    @PatchMapping("/{id}")
    public Cutomer patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) throws IOException {
        return cutomerService.patch(id, patchNode);
    }

    @PatchMapping
    public List<UUID> patchMany(@RequestParam @Valid List<UUID> ids, @RequestBody JsonNode patchNode) throws IOException {
        return cutomerService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    public Cutomer delete(@PathVariable UUID id) {
        return cutomerService.delete(id);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<UUID> ids) {
        cutomerService.deleteMany(ids);
    }
}
