package com.example.order.repository;

import com.example.order.entity.Order;
import com.example.order.entity.OrderMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

interface OrderQueryRepository {
    Page<Order> findAllByUserId(Pageable pageable, int id);

    Page<Order> findAllByShopId(Pageable pageable, int id);

    OrderMenu findOrderMenu(int id);

    Page<OrderMenu> findAllOrderMenusByOrderId(Pageable pageable, int id);
}
