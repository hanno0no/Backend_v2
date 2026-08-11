package com.example.HNN.repository;

import com.example.HNN.domain.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Integer> {

    List<Material> findByIsActiveTrue();
}