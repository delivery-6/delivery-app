package com.example.review.dto.response;


import com.example.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ReviewResponseSimpleDto {
    private String userName;
    private Integer rating;
    private String description;
    private LocalDateTime updateAt;

    public static Page<ReviewResponseSimpleDto> from(Page<Review> reviewPage) {
        List<ReviewResponseSimpleDto> content = reviewPage.getContent().stream()
                .map(review -> new ReviewResponseSimpleDto(
                        review.getOrder().getUser().getName(),
                        review.getRating(),
                        review.getDescription(),
                        review.getUpdatedAt()
                ))
                .collect(Collectors.toList());

        return new PageImpl<>(content, reviewPage.getPageable(), reviewPage.getTotalElements());
    }

}
