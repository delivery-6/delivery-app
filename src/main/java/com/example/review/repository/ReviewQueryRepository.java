package com.example.review.repository;

import com.example.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryRepository {
    Page<Review> findAllByShopId(int shopId, Pageable pageable);

    Page<Review> findAllByUserId(Pageable pageable, int userId);
}