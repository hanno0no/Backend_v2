package com.example.HNN.service;

import com.example.HNN.domain.EventInfo;
import com.example.HNN.dto.DashboardResponse;
import com.example.HNN.exception.NoActiveEventException;
import com.example.HNN.repository.EventInfoRepository;
import com.example.HNN.repository.MessageRepository;
import com.example.HNN.repository.OrderRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    private final EventInfoRepository eventInfoRepository;
    private final OrderRepository orderRepository;
    private final MessageRepository messageRepository;

    public DashboardService(EventInfoRepository eventInfoRepository,
                            OrderRepository orderRepository,
                            MessageRepository messageRepository) {
        this.eventInfoRepository = eventInfoRepository;
        this.orderRepository = orderRepository;
        this.messageRepository = messageRepository;
    }

    public DashboardResponse getDashboard() {
        EventInfo activeEvent = eventInfoRepository.findByIsOpenTrue()
                .orElseThrow(NoActiveEventException::new);

        List<String> completedTeam = orderRepository.findCompletedTeamCodes(
                "print_complete",
                PageRequest.of(0, activeEvent.getCompletedLimit()));

        List<String> waitingTeam = orderRepository.findWaitingTeamCodes(
                List.of("accepted", "design_complete"),
                PageRequest.of(0, activeEvent.getWaitingLimit()));

        List<String> emergencyMessages = messageRepository
                .findByIsEmergencyTrueAndIsDisplayTrueOrderByCreatedAtDesc()
                .stream().map(com.example.HNN.domain.Message::getContent).toList();

        List<String> messages = messageRepository
                .findByIsEmergencyFalseAndIsDisplayTrueOrderByCreatedAtDesc()
                .stream().map(com.example.HNN.domain.Message::getContent).toList();

        return new DashboardResponse(
                completedTeam,
                waitingTeam,
                activeEvent.getEndTime().toString(),
                emergencyMessages,
                messages
        );
    }
}