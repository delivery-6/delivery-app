package com.example.order.repository;

import com.example.order.entity.Order;
import com.example.order.entity.OrderMenu;

import java.util.List;

interface OrderQueryRepository {
    List<Order> findAllByUserId(int id);

    List<Order> findAllByShopId(int id);

    OrderMenu findOrderMenu(int id);

    List<OrderMenu> findAllOrderMenusByOrderId(int id);
}
