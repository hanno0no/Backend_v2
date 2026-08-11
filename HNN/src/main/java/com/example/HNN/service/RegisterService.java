package com.example.HNN.service;

import com.example.HNN.domain.Material;
import com.example.HNN.domain.Orders;
import com.example.HNN.domain.State;
import com.example.HNN.domain.Team;
import com.example.HNN.dto.RegisterRequest;
import com.example.HNN.dto.RegisterResponse;
import com.example.HNN.exception.MaterialNotFoundException;
import com.example.HNN.exception.TeamNotFoundException;
import com.example.HNN.repository.MaterialRepository;
import com.example.HNN.repository.OrderRepository;
import com.example.HNN.repository.StateRepository;
import com.example.HNN.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {

    private static final String INITIAL_STATE = "submitted";

    private final OrderRepository orderRepository;
    private final TeamRepository teamRepository;
    private final MaterialRepository materialRepository;
    private final StateRepository stateRepository;

    public RegisterService(OrderRepository orderRepository,
                           TeamRepository teamRepository,
                           MaterialRepository materialRepository,
                           StateRepository stateRepository) {
        this.orderRepository = orderRepository;
        this.teamRepository = teamRepository;
        this.materialRepository = materialRepository;
        this.stateRepository = stateRepository;
    }

    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        // 등록된 팀인지 확인
        Team team = teamRepository.findById(request.teamNum())
                .orElseThrow(() -> new TeamNotFoundException(request.teamNum()));

        // 활성 재질인지 확인
        Material material = materialRepository
                .findByMaterialAndIsActiveTrue(request.material())
                .orElseThrow(() -> new MaterialNotFoundException(request.material()));

        // 초기 상태 조회
        State submitted = stateRepository.findByState(INITIAL_STATE)
                .orElseThrow(() -> new IllegalStateException("초기 상태(submitted)가 등록되어 있지 않습니다"));

        // 1차 저장 — orderId를 발급받기 위해 파일명은 임시로 비워둠
        Orders order = new Orders(null, team, material, submitted);
        orderRepository.save(order);

        // 발급된 orderId로 파일명 생성 후 갱신
        String fileName = generateFileName(team.getTeamNum(), order.getOrderId(), material.getFileCode());
        order.updateFileName(fileName);

        return new RegisterResponse(
                order.getOrderId(),
                fileName,
                "접수가 완료되었습니다."
        );
    }

    // BR-03: {팀번호}_{주문ID}{재질코드}
    private String generateFileName(String teamNum, Long orderId, String fileCode) {
        String suffix = (fileCode == null) ? "" : fileCode;
        return teamNum + "_" + orderId + suffix;
    }
}