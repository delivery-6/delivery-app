package com.example.review.repository;

import com.example.review.entity.Review;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.order.entity.QOrder.order;
import static com.example.review.entity.QReview.review;
import static com.example.shop.entity.QShop.shop;
import static com.example.user.entity.QUser.user;

@Repository
class ReviewQueryRepositoryImpl implements ReviewQueryRepository {

    private final JPQLQueryFactory queryFactory;

    public ReviewQueryRepositoryImpl(JPQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<Review> findAllByShopId(int shopId, Pageable pageable) {
        JPQLQuery<Review> query = queryFactory
                .selectFrom(review)
                .join(review.order, order).fetchJoin()       // Review → Order
                .join(order.user, user).fetchJoin()          // Order → User
                .join(shop).on(user.id.eq(shop.user.id))     // User → Shop
                .where(shop.id.eq(shopId))                   // Shop ID 조건
                .orderBy(review.updatedAt.desc());           // 최신순 정렬

        // 전체 개수 조회
        long total = query.fetchCount();

        // 페이징 처리
        List<Review> content = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(content, pageable, total);
    }



    @Override
    public Page<Review> findAllByUserId(Pageable pageable, int userId) {
        JPQLQuery<Review> query = queryFactory
                .selectFrom(review)
                .join(review.order, order).fetchJoin()
                .join(order.user, user).fetchJoin()
                .where(user.id.eq(userId));

        long total = query.fetchCount(); // 전체 개수
        List<Review> content = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch(); // 현재 페이지 데이터

        return new PageImpl<>(content, pageable, total);
    }
}
