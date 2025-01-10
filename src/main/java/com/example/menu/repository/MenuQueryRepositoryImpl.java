package com.example.menu.repository;

import com.example.menu.entity.Menu;
import com.example.utils.QuerydslUtil;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static com.example.menu.entity.QMenu.menu;
import static com.example.shop.entity.QShop.shop;

@Repository
class MenuRepositoryImpl implements MenuQueryRepository {
    private final JPQLQueryFactory queryFactory;

    public MenuRepositoryImpl(JPQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<Menu> findAllByShopId(Pageable pageable, int id) {
        var result = queryFactory
                .selectFrom(menu)
                .join(menu.shop, shop).fetchJoin()
                .where(menu.shop.id.eq(id));
        return QuerydslUtil.fetchPage(result, menu, pageable);
    }
}