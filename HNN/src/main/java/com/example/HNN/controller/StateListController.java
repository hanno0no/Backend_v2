package com.example.HNN.controller;

import com.example.HNN.dto.StateResponse;
import com.example.HNN.service.StateListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class StateListController {

    private final StateListService stateListService;

    public StateListController(StateListService stateListService) {
        this.stateListService = stateListService;
    }

    @GetMapping("/states")
    public ResponseEntity<List<StateResponse>> getStates() {
        return ResponseEntity.ok(stateListService.getAllStates());
    }
}