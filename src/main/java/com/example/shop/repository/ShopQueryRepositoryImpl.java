package com.example.shop.repository;

import com.example.shop.entity.Shop;
import com.example.utils.QuerydslUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.example.shop.entity.QShop.shop;

@RequiredArgsConstructor
public class ShopQueryRepositoryImpl implements ShopQueryRepository {

    private final JPAQueryFactory queryFactory;

    // 삭제되지 않은 특정 가게 조회
    @Override
    public Shop findActiveById(int id) {
        return queryFactory
                .selectFrom(shop)
                .where(shop.id.eq(id), shop.isDeleted.isFalse())  // 삭제되지 않은 가게만
                .fetchOne();
    }

    // 페이지네이션을 적용한 가게 조회
    @Override
    public Page<Shop> findActiveShops(String name, Pageable pageable) {
        var query = queryFactory
                .selectFrom(shop)
                .where(shop.isDeleted.isFalse());  // 삭제되지 않은 가게만

        // 이름으로 필터링
        if (name != null && !name.isEmpty()) {
            query.where(shop.name.containsIgnoreCase(name));  // 이름으로 필터링
        }

        // QuerydslUtil.fetchPage 메서드를 사용하여 페이지네이션 적용
        return QuerydslUtil.fetchPage(query, shop, pageable);  // fetchPage 사용
    }

    // 특정 가게 조회 (삭제되지 않은 가게만)
    @Override
    public Shop findActiveShopById(int id) {
        return queryFactory
                .selectFrom(shop)
                .where(shop.id.eq(id), shop.isDeleted.isFalse())  // 삭제되지 않은 가게만
                .fetchOne();
    }
}
