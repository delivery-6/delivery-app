package com.example.review.controller;

import com.example.review.dto.request.ReviewCreateRequestDto;
import com.example.review.dto.request.ReviewUpdateRequestDto;
import com.example.review.dto.response.ReviewPageResponseDto;
import com.example.review.dto.response.ReviewResponseDetailDto;
import com.example.review.service.ReviewService;
import com.example.utils.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/shops/{shopId}/orders/{orderId}/reviews")
    public ResponseEntity<ReviewResponseDetailDto> create(@PathVariable int shopId,@PathVariable int orderId, @RequestBody ReviewCreateRequestDto requestDto) {
        Integer userId = AuthUtil.getId();
        if (userId == null) {
            return ResponseEntity.status(401).build();
        }

        ReviewResponseDetailDto responseDto = reviewService.create(shopId, userId,orderId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
    @PatchMapping("/reviews/{reviewId}")
    public ResponseEntity<ReviewResponseDetailDto> update(@PathVariable int reviewId, @RequestBody ReviewUpdateRequestDto requestDto){
        Integer userId = AuthUtil.getId();
        if (userId == null) {
            return ResponseEntity.status(401).build();
        }

        ReviewResponseDetailDto responseDto = reviewService.update(userId,reviewId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/shops/{shopId}/page/{page}")
    public List<ReviewPageResponseDto> findReviewByPage(@PathVariable int shopId, @PathVariable int page){
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "updateAt"));

        return reviewService.findReviewsByShopId(shopId, pageable);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public void delete(@PathVariable int reviewId){
        reviewService.delete(reviewId);
    }


}
