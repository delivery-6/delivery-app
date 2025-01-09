package com.example.review.dto.request;

public record ReviewUpdateRequestDto (
        Integer rating,
        String description
) {
}