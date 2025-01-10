package com.example.shop.repository;

import com.example.shop.entity.Shop;
import com.example.user.entity.User;
import com.example.utils.QuerydslUtil;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static com.example.menu.entity.QMenu.menu;
import static com.example.shop.entity.QShop.shop;

@Repository
public class ShopRepositoryCustomImpl implements ShopRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Autowired
    public ShopRepositoryCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<Shop> findAllActive(Pageable pageable) {
        JPAQuery<Shop> result = queryFactory.selectFrom(shop)
                .where(shop.isDeleted.eq(false));
        return QuerydslUtil.fetchPage(result, shop, pageable);
    }

    @Override
    public Shop findActiveById(int id) {
        return queryFactory.selectFrom(shop)
                .where(shop.id.eq(id).and(shop.isDeleted.eq(false)))
                .fetchOne();
    }

    @Override
    public Page<Shop> findByNameContainingAndIsDeletedFalse(Pageable pageable, String name) {
        JPAQuery<Shop> result = queryFactory.selectFrom(shop)
                .where(shop.name.contains(name).and(shop.isDeleted.eq(false)));
        return QuerydslUtil.fetchPage(result, shop, pageable);
    }

    @Override
    public long countByUserAndIsDeletedFalse(User user) {
        return queryFactory.selectFrom(shop)
                .where(shop.user.eq(user).and(shop.isDeleted.eq(false)))
                .fetchCount();
    }
}
