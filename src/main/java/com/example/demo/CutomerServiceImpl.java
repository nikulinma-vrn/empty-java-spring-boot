package com.example.demo;

import com.example.demo.entity.Cutomer;
import com.example.demo.repository.CutomerRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CutomerServiceImpl implements CutomerService {

    private final CutomerRepository cutomerRepository;

    private final ObjectMapper objectMapper;

    public CutomerServiceImpl(CutomerRepository cutomerRepository,
                              ObjectMapper objectMapper) {
        this.cutomerRepository = cutomerRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Page<Cutomer> getAll(CutomerFilter filter, Pageable pageable) {
        Specification<Cutomer> spec = filter.toSpecification();
        return cutomerRepository.findAll(spec, pageable);
    }

    @Override
    public Cutomer getOne(UUID id) {
        Optional<Cutomer> cutomerOptional = cutomerRepository.findById(id);
        return cutomerOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));
    }

    @Override
    public List<Cutomer> getMany(List<UUID> ids) {
        return cutomerRepository.findAllById(ids);
    }

    @Override
    public Cutomer create(Cutomer cutomer) {
        return cutomerRepository.save(cutomer);
    }

    @Override
    public Cutomer patch(UUID id, JsonNode patchNode) throws IOException {
        Cutomer cutomer = cutomerRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        objectMapper.readerForUpdating(cutomer).readValue(patchNode);

        return cutomerRepository.save(cutomer);
    }

    @Override
    public List<UUID> patchMany(List<UUID> ids, JsonNode patchNode) throws IOException {
        Collection<Cutomer> cutomers = cutomerRepository.findAllById(ids);

        for (Cutomer cutomer : cutomers) {
            objectMapper.readerForUpdating(cutomer).readValue(patchNode);
        }

        List<Cutomer> resultCutomers = cutomerRepository.saveAll(cutomers);
        return resultCutomers.stream()
                .map(Cutomer::getId)
                .toList();
    }

    @Override
    public Cutomer delete(UUID id) {
        Cutomer cutomer = cutomerRepository.findById(id).orElse(null);
        if (cutomer != null) {
            cutomerRepository.delete(cutomer);
        }
        return cutomer;
    }

    @Override
    public void deleteMany(List<UUID> ids) {
        cutomerRepository.deleteAllById(ids);
    }
}
