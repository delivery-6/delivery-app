package com.example.review.repository;

import com.example.menu.entity.Menu;
import com.example.order.entity.Order;
import com.example.review.entity.Review;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.menu.entity.QMenu.menu;
import static com.example.shop.entity.QShop.shop;


public interface ReviewRepository extends JpaRepository<Review, Integer>, ReviewQueryRepository {

    boolean existsByOrder(Order order);
    Page<Review> findByShopId(int shopId, Pageable pageable);
}



