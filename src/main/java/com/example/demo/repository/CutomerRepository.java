package com.example.demo.repository;

import com.example.demo.entity.Cutomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface CutomerRepository extends JpaRepository<Cutomer, UUID>, JpaSpecificationExecutor<Cutomer> {
    Cutomer findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update Cutomer c set c.id = ?1 where c.email = ?2")
    int updateIdByEmail(UUID id, String email);
}