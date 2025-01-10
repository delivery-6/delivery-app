package com.example.review.repository;

import com.example.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

interface ReviewQueryRepository {
    Page<Review> findAllByShopId(int id);
    Page<Review> findAllByUserId(Pageable pageable, int id);
}