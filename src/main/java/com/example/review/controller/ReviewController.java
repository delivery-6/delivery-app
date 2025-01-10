package com.example.review.controller;

import com.example.review.dto.request.ReviewCreateRequestDto;
import com.example.review.dto.request.ReviewUpdateRequestDto;
import com.example.review.dto.response.ReviewResponseSimpleDto;
import com.example.review.dto.response.ReviewResponseDetailDto;
import com.example.review.service.ReviewService;
import com.example.utils.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 생성
    @PostMapping("/order/{orderId}")
    public ResponseEntity<ReviewResponseDetailDto> create(
            @PathVariable int orderId,
            @RequestBody ReviewCreateRequestDto requestDto) {
        Integer userId = AuthUtil.getId();
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        ReviewResponseDetailDto responseDto = reviewService.create(shopId, userId, orderId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 리뷰 수정
    @PatchMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDetailDto> update(
            @PathVariable int reviewId,
            @RequestBody ReviewUpdateRequestDto requestDto) {
        Integer userId = AuthUtil.getId();
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        ReviewResponseDetailDto responseDto = reviewService.update(userId, reviewId, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 특정 상점의 리뷰 페이지 조회
    @GetMapping("/page/{page}")
    public Page<ReviewResponseSimpleDto> findReviewByPage(
            @PathVariable int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "updateAt"));
        return reviewService.findReviewsByShopId(pageable);
    }



    // 리뷰 삭제
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> delete(@PathVariable int reviewId) {
        reviewService.delete(reviewId);
        return ResponseEntity.noContent().build();
    }
}
