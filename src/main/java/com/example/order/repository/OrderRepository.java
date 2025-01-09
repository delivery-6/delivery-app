package com.example.order.repository;

import com.example.order.entity.Order;
import com.example.order.entity.OrderMenu;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.menu.entity.QMenu.menu;
import static com.example.order.entity.QOrder.order;
import static com.example.order.entity.QOrderMenu.orderMenu;
import static com.example.user.entity.QUser.user;

public interface OrderRepository extends JpaRepository<Order, Integer>, OrderQueryRepository {

}

interface OrderQueryRepository {
    List<Order> findAllByUserId(int id);

    List<Order> findAllByShopId(int id);

    OrderMenu findOrderMenu(int id);

    List<OrderMenu> findAllOrderMenusByOrderId(int id);
}

@Repository
class OrderRepositoryImpl implements OrderQueryRepository {
    private final JPQLQueryFactory queryFactory;

    public OrderRepositoryImpl(JPQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Order> findAllByUserId(int id) {
        return queryFactory
                .selectFrom(order)
                .join(order.user, user).fetchJoin()
                .where(order.user.id.eq(id))
                .fetch();
    }

    @Override
    public List<Order> findAllByShopId(int id) {
        return queryFactory
                .selectFrom(order)
                .join(order.orderMenus, orderMenu).fetchJoin()
                .join(order.user).fetchJoin()
                .where(orderMenu.menu.shop.id.eq(id))
                .fetch();
    }

    @Override
    public OrderMenu findOrderMenu(int id) {
        return queryFactory
                .selectFrom(orderMenu)
                .where(orderMenu.id.eq(id))
                .fetchOne();
    }

    @Override
    public List<OrderMenu> findAllOrderMenusByOrderId(int id) {
        return queryFactory
                .selectFrom(orderMenu)
                .join(orderMenu.menu, menu).fetchJoin()
                .where(orderMenu.order.id.eq(id))
                .fetch();
    }
}