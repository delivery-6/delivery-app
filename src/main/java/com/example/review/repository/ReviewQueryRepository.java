package com.example.review.repository;

import com.example.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

interface ReviewQueryRepository {
    List<Review> findAllByShopId(int id);
    Page<Review> findAllByUserId(Pageable pageable, int id);
}