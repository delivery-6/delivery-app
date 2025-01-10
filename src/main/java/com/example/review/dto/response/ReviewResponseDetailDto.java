package com.example.review.dto.response;

import com.example.review.entity.Review;

import java.time.LocalDateTime;

public record ReviewResponseDetailDto(
        int id,
        int shopId,
        int userId,
        String userName,
        int rating,
        String description,
        LocalDateTime createAt,
        LocalDateTime updateAt
) {

    public static ReviewResponseDetailDto from(Review review) {
        return new ReviewResponseDetailDto(
                review.getId(),
                review.getShop().getId(),
                review.getOrder().getUser().getId(),
                review.getOrder().getUser().getName(),
                review.getRating(),
                review.getDescription(),
                review.getCreatedAt(),
                review.getUpdatedAt());
    }


}
