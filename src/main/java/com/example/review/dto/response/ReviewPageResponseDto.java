package com.example.review.dto.response;



import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.example.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor
public class ReviewPageResponseDto {
    private String userName;
    private Integer rating;
    private String description;
    private LocalDateTime updateAt;

    public static List<ReviewPageResponseDto> from(Page<Review> reviewPage) {
        return reviewPage.getContent().stream()
                .map(review -> new ReviewPageResponseDto(
                        review.getOrder().getUser().getName(),
                        review.getRating(),
                        review.getDescription(),
                        review.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }
}
