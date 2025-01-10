package com.example.review.repository;

import com.example.menu.entity.Menu;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.menu.entity.QMenu.menu;
import static com.example.shop.entity.QShop.shop;

@Repository
class ReviewRepositoryImpl implements ReviewQueryRepository {
    private final JPQLQueryFactory queryFactory;

    public ReviewRepositoryImpl(JPQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Menu> findAllByShopId(int id) {
        return queryFactory
                .selectFrom(menu)
                .join(menu.shop, shop).fetchJoin()
                .where(menu.shop.id.eq(id))
                .fetch();
    }
}