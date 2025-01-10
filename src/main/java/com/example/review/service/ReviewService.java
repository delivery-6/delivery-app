package com.example.review.service;

import com.example.order.entity.Order;
import com.example.order.repository.OrderRepository;
import com.example.review.dto.request.ReviewCreateRequestDto;
import com.example.review.dto.request.ReviewUpdateRequestDto;
import com.example.review.dto.response.ReviewResponseSimpleDto;
import com.example.review.dto.response.ReviewResponseDetailDto;
import com.example.review.entity.Review;
import com.example.review.repository.ReviewRepository;
import com.example.shop.entity.Shop;
import com.example.shop.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private OrderRepository orderRepository;

    public ReviewResponseDetailDto create(int shopId, int userId, int orderId, ReviewCreateRequestDto requestDto) {
        Shop shop = shopRepository.findById(shopId).orElseThrow();
        Order order = orderRepository.findById(orderId).orElseThrow();
        if (order.getUser().getId() != userId) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 주문 사용자가 아닙니다.");
        }
        if (reviewRepository.existsByOrder(order)) {
            throw new IllegalArgumentException("리뷰가 이미 작성되어있습니다.");
        }
        Review review = reviewRepository.save(Review.from(shop, order, requestDto.rating(), requestDto.description()));
        return ReviewResponseDetailDto.from(review);
    }


    public ReviewResponseDetailDto update(int userId, int reviewId, ReviewUpdateRequestDto requestDto) {
        Review review = reviewRepository.findById(reviewId).orElseThrow();

        if(review.getOrder().getUser().getId() != userId){//업데이트 하는 리뷰의 해당 사용자 맞는지 검사해야함
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 리뷰 사용자가 아닙니다.");
        }

        review = reviewRepository.save(review.partialUpdate(requestDto));

        return ReviewResponseDetailDto.from(review);
    }

    public Page<ReviewResponseSimpleDto> findReviewsByShopId(int shopId, Pageable pageable) {
        // Repository에서 Page<Review>를 가져옴
        Page<Review> reviews = reviewRepository.findByShopId(shopId, pageable);

        // Page<Review>를 Page<ReviewPageResponseDto>로 변환
        return ReviewResponseSimpleDto.from(reviews);
    }

    public void delete(int reviewId){
        reviewRepository.deleteById(reviewId);
    }

}
