package com.example.review.dto.request;


public record ReviewCreateRequestDto(
        Integer rating,
        String description
) {
}
