package com.example.review.repository;

import com.example.order.entity.Order;
import com.example.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ReviewRepository extends JpaRepository<Review, Integer>, ReviewQueryRepository {

    boolean existsByOrder(Order order);

}
