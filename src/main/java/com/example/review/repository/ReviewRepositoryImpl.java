package com.example.review.repository;

import com.example.review.entity.Review;
import com.example.utils.QuerydslUtil;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static com.example.user.entity.QUser.user;
import static com.example.order.entity.QOrder.order;
import static com.example.review.entity.QReview.review;

@Repository
class ReviewRepositoryImpl implements ReviewQueryRepository {
    private final JPQLQueryFactory queryFactory;

    public ReviewRepositoryImpl(JPQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<Review> findAllByUserId(Pageable pageable, int id) {
        JPQLQuery<Review> query = queryFactory
                .selectFrom(review)
                .join(review.order, order).fetchJoin()
                .join(order.user, user).fetchJoin()
                .where(user.id.eq(id));
        return QuerydslUtil.fetchPage(pageable, review, query);
    }


}