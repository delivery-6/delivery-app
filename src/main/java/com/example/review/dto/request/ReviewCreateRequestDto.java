package com.example.review.dto.request;


public record ReviewCreateRequestDto(
        int orderId,
        int rating,
        String description
) {
}
