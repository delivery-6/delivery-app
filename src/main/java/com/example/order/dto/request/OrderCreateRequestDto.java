package com.example.order.dto.request;

import java.util.Map;

public record OrderCreateRequestDto(
        Map<Integer, Integer> menuInfo
) {
}