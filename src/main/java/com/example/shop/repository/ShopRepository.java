package com.example.shop.repository;

import com.example.shop.entity.Shop;
import com.example.utils.QuerydslUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static com.example.shop.entity.QShop.shop;

@RequiredArgsConstructor
@Repository
public class ShopQueryRepositoryImpl implements ShopQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Shop> findActiveShops(Pageable pageable) {
        var query = queryFactory
                .selectFrom(shop)
                .where(shop.isDeleted.isFalse());

        return QuerydslUtil.fetchPage(query, shop, pageable);
    }

    @Override
    public Shop findActiveById(int id) {
        return queryFactory
                .selectFrom(shop)
                .where(shop.id.eq(id), shop.isDeleted.isFalse())
                .fetchOne();
    }
}
