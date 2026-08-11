package com.example.HNN.service;

import com.example.HNN.domain.Material;
import com.example.HNN.repository.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public List<String> getActiveMaterialNames() {
        return materialRepository.findByIsActiveTrue()
                .stream()
                .map(Material::getMaterial)
                .toList();
    }
}