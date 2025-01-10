package com.example.shop.repository;

import com.example.shop.entity.Shop;
import com.example.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import static com.example.shop.entity.QShop.shop;

import java.util.List;

@Repository
public class ShopRepositoryCustomImpl implements ShopRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Autowired
    public ShopRepositoryCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    private QShop shop = QShop.shop;

    @Override
    public List<Shop> findAllActive() {
        return queryFactory.selectFrom(shop)
                .where(shop.isDeleted.eq(false))
                .fetch();
    }

    @Override
    public Shop findActiveById(int id) {
        return queryFactory.selectFrom(shop)
                .where(shop.id.eq(id).and(shop.isDeleted.eq(false)))
                .fetchOne();
    }

    @Override
    public List<Shop> findByNameContainingAndIsDeletedFalse(String name) {
        return queryFactory.selectFrom(shop)
                .where(shop.name.contains(name).and(shop.isDeleted.eq(false)))
                .fetch();
    }

    @Override
    public long countByUserAndIsDeletedFalse(User user) {
        return queryFactory.selectFrom(shop)
                .where(shop.user.eq(user).and(shop.isDeleted.eq(false)))
                .fetchCount();
    }
}
