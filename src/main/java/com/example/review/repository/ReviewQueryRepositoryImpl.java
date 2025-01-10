package com.example.review.repository;

import com.example.review.entity.Review;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.user.entity.QUser.user;
import static com.example.order.entity.QOrder.order;
import static com.example.review.entity.QReview.review;

@Repository
class ReviewQueryRepositoryImpl implements ReviewQueryRepository {
    private final JPQLQueryFactory queryFactory;

    public ReviewQueryRepositoryImpl(JPQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<Review> findAllByShopId(int id) {
      return null;
    }

    @Override
    public Page<Review> findAllByUserId(Pageable pageable, int id) {
        JPQLQuery<Review> query = queryFactory
                .selectFrom(review)
                .join(review.order, order).fetchJoin()
                .join(order.user, user).fetchJoin()
                .where(user.id.eq(id));

        long total = query.fetchCount(); // 전체 개수
        List<Review> content = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch(); // 현재 페이지 데이터

        return new PageImpl<>(content, pageable, total);
    }

}