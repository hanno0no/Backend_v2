package com.example.HNN.controller;

import com.example.HNN.dto.OrderStatusResponse;
import com.example.HNN.service.CheckStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CheckStatusController {

    private final CheckStatusService checkStatusService;

    public CheckStatusController(CheckStatusService checkStatusService) {
        this.checkStatusService = checkStatusService;
    }

    @GetMapping("/checkStatus")
    public ResponseEntity<List<OrderStatusResponse>> checkStatus(
            @RequestParam String teamNum) {
        return ResponseEntity.ok(checkStatusService.getOrdersByTeam(teamNum));
    }
}