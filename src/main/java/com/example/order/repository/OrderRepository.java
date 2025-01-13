package com.example.order.repository;

import com.example.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer>, OrderQueryRepository {
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.orderMenus WHERE o.id = :id")
    Optional<Order> findByIdWithMenus(@Param("id") int id);
}