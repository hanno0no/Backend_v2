package com.example.HNN.repository;

import com.example.HNN.domain.Orders;
import org.springframework.data.jpa.domain.Specification;

public class OrderSpecifications {

    public static Specification<Orders> teamNumEquals(String teamNum) {
        return (root, query, cb) ->
                teamNum == null ? null : cb.equal(root.get("team").get("teamNum"), teamNum);
    }

    public static Specification<Orders> materialEquals(String material) {
        return (root, query, cb) ->
                material == null ? null : cb.equal(root.get("material").get("material"), material);
    }

    public static Specification<Orders> adminEquals(String admin) {
        return (root, query, cb) ->
                admin == null ? null : cb.equal(root.get("adminUser").get("username"), admin);
    }

    public static Specification<Orders> stateEquals(String state) {
        return (root, query, cb) ->
                state == null ? null : cb.equal(root.get("state").get("state"), state);
    }
}