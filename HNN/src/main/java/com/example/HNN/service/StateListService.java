package com.example.HNN.service;

import com.example.HNN.dto.StateResponse;
import com.example.HNN.repository.StateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StateListService {

    private final StateRepository stateRepository;

    public StateListService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Transactional(readOnly = true)
    public List<StateResponse> getAllStates() {
        return stateRepository.findAll().stream()
                .map(s -> new StateResponse(s.getStateNum(), s.getState()))
                .toList();
    }
}