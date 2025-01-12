package com.example.review.service;

import com.example.order.entity.Order;
import com.example.order.repository.OrderRepository;
import com.example.review.dto.request.ReviewCreateRequestDto;
import com.example.review.dto.request.ReviewUpdateRequestDto;
import com.example.review.dto.response.ReviewResponseDetailDto;
import com.example.review.dto.response.ReviewResponseSimpleDto;
import com.example.review.entity.Review;
import com.example.review.repository.ReviewRepository;
import com.example.shop.repository.ShopRepository;
import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import com.example.utils.AuthUtil;
import com.example.utils.PageQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ShopRepository shopRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public ReviewResponseDetailDto create(
            ReviewCreateRequestDto requestDto
    ) {
        Order order = orderRepository.findById(requestDto.orderId()).orElseThrow(() ->
                new RuntimeException("리뷰 작성에 필요한 주문 ID: " + requestDto.orderId() + " 는 존재하지 않는 주문입니다."));
//        int orderMenuId = order.getOrderMenus().stream().findAny().get().getId();
//        Shop shop = orderRepository.findOrderMenu(orderMenuId).getMenu().getShop();
        if (order.getUser().getId() != AuthUtil.getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 주문 사용자가 아닙니다.");
        }

        if (reviewRepository.existsByOrder(order)) {
            throw new IllegalArgumentException("리뷰가 이미 작성되어있습니다.");
        }
        Review review = Review.from(
                order,
                requestDto.rating()
                , requestDto.description());
        review = reviewRepository.save(review);

        return ReviewResponseDetailDto.from(review);
    }


    public ReviewResponseDetailDto update(int reviewId, ReviewUpdateRequestDto requestDto) {
        //TODO: GEH 추가 후 수정
        Review review = reviewRepository.findById(reviewId).orElseThrow();

        //업데이트 하는 리뷰의 해당 사용자 맞는지 검사해야함
        if(review.getOrder().getUser().getId() != AuthUtil.getId()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 리뷰 사용자가 아닙니다.");
        }

        review = reviewRepository.save(review.partialUpdate(requestDto));
        return ReviewResponseDetailDto.from(review);
    }

    public Page<ReviewResponseSimpleDto> getReviewsByShopId(int shopId, Pageable pageable) {
        return  ReviewResponseSimpleDto.pageFrom(reviewRepository.findAllByShopId(shopId, pageable));
    }

    public com.example.utils.Page<ReviewResponseSimpleDto> findAllByUserId(PageQuery pageQuery){
        Page<Review> page = reviewRepository.findAllByUserId(pageQuery.toPageable(), AuthUtil.getId());
        return com.example.utils.Page.from(
                page.map(entity ->
                        ReviewResponseSimpleDto.from(getAuthUser().getName(), entity)));
    }

    public void delete(int reviewId){
        reviewRepository.deleteById(reviewId);
    }

    private User getAuthUser(){
        //TODO: GlobalExceptionHandler 구현 후 수정
        return userRepository.findById(AuthUtil.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
    }

}
