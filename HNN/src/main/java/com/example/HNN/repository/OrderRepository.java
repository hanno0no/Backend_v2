package com.example.HNN.repository;

import com.example.HNN.domain.Orders;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    // completedTeam: print_complete, hidden 제외, limit 적용
    @Query("SELECT o.team.teamNum FROM Orders o " +
            "WHERE o.state.state = :state AND o.hiddenFromDashboard = false " +
            "GROUP BY o.team.teamNum " +
            "ORDER BY MAX(o.updatedAt) DESC")
    List<String> findCompletedTeamCodes(@Param("state") String state, Pageable pageable);

    // waitingTeam: accepted + design_complete, limit 적용
    @Query("SELECT o.team.teamNum FROM Orders o " +
            "WHERE o.state.state IN :states " +
            "GROUP BY o.team.teamNum " +
            "ORDER BY MAX(o.updatedAt) DESC")
    List<String> findWaitingTeamCodes(@Param("states") List<String> states, Pageable pageable);

    // 특정 팀의 주문 목록 (접수 최신순)
    List<Orders> findByTeam_TeamNumOrderByOrderedAtDesc(String teamNum);
}