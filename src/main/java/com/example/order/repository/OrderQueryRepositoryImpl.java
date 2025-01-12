package com.example.order.repository;

import com.example.order.entity.Order;
import com.example.order.entity.OrderMenu;
import com.example.utils.QuerydslUtil;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.menu.entity.QMenu.menu;
import static com.example.order.entity.QOrder.order;
import static com.example.order.entity.QOrderMenu.orderMenu;
import static com.example.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
class OrderQueryRepositoryImpl implements OrderQueryRepository {
    private final JPQLQueryFactory queryFactory;

    @Override
    public Page<Order> findAllByUserId(Pageable pageable, int id) {
        JPQLQuery<Order> query = queryFactory
                .selectFrom(order)
                .join(order.user, user).fetchJoin()
                .where(order.user.id.eq(id));
        return QuerydslUtil.fetchPage(query, order, pageable);
    }

    @Override
    public Page<Order> findAllByShopId(Pageable pageable, int id) {
        JPQLQuery<Order> query = queryFactory
                .selectFrom(order)
                .join(order.orderMenus, orderMenu).fetchJoin()
                .join(order.user).fetchJoin()
                .where(orderMenu.menu.shop.id.eq(id));
        return QuerydslUtil.fetchPage(query, order, pageable);
    }

    @Override
    public OrderMenu findOrderMenu(int id) {
        return queryFactory
                .selectFrom(orderMenu)
                .where(orderMenu.id.eq(id))
                .fetchOne();
    }

    @Override
    public Page<OrderMenu> findAllOrderMenusByOrderId(Pageable pageable, int id) {
        JPQLQuery<OrderMenu> query = queryFactory
                .selectFrom(orderMenu)
                .join(orderMenu.menu, menu).fetchJoin()
                .where(orderMenu.order.id.eq(id));
        return QuerydslUtil.fetchPage(query, orderMenu, pageable);
    }
}
