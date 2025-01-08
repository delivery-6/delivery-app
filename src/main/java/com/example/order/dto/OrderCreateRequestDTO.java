package com.example.order.dto;

import java.util.Map;

public record OrderCreateRequestDTO(
        Map<Integer, Integer> menuInfo
) {
}