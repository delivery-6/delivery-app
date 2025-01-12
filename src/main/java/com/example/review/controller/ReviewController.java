package com.example.review.controller;

import com.example.review.dto.request.ReviewCreateRequestDto;
import com.example.review.dto.request.ReviewUpdateRequestDto;
import com.example.review.dto.response.ReviewResponseDetailDto;
import com.example.review.dto.response.ReviewResponseSimpleDto;
import com.example.review.service.ReviewService;
import com.example.utils.PageQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 생성
    @PostMapping
    public ResponseEntity<ReviewResponseDetailDto> create(
            @RequestBody ReviewCreateRequestDto requestDto
    ) {
        ReviewResponseDetailDto responseDto = reviewService.create(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 리뷰 수정
    @PatchMapping("/{id}")
    public ResponseEntity<ReviewResponseDetailDto> update(
            @PathVariable int id,
            @RequestBody ReviewUpdateRequestDto requestDto
    ) {
        ReviewResponseDetailDto responseDto = reviewService.update(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 특정 상점의 리뷰 페이지 조회
    @GetMapping("/shops/{shopId}")
    public ResponseEntity<Page<ReviewResponseSimpleDto>> findAllReviewByShopId(
            @PathVariable int shopId, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "updatedAt"));
        Page<ReviewResponseSimpleDto> reviews = reviewService.getReviewsByShopId(shopId, pageable);
        return ResponseEntity.ok(reviews);
    }

    // 특정 유저의 모든 리뷰 조회
    @GetMapping
    public ResponseEntity<com.example.utils.Page<ReviewResponseSimpleDto>> findAllReviewByUserId(PageQuery pageQuery){
        return ResponseEntity.ok(reviewService.findAllByUserId(pageQuery));
    }

    // 리뷰 삭제
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> delete(@PathVariable int reviewId) {
        reviewService.delete(reviewId);
        return ResponseEntity.noContent().build();
    }
}
