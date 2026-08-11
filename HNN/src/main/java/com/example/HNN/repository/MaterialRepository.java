package com.example.HNN.repository;

import com.example.HNN.domain.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MaterialRepository extends JpaRepository<Material, Integer> {

    List<Material> findByIsActiveTrue();

    Optional<Material> findByMaterialAndIsActiveTrue(String material);
}