package com.example.review.entity;

import com.example.common.entity.BaseEntity;
import com.example.order.entity.Order;
import com.example.review.dto.request.ReviewUpdateRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false)
    private int rating;

    @Column(nullable = false, length = 50)
    private String description;

    public Review(Order order, Integer rating, String description) {
        this.order = order;
        this.rating = rating;
        this.description = description;
    }

    public Review partialUpdate(ReviewUpdateRequestDto dto){
        this.rating = dto.rating();
        this.description = dto.description();
        return this;
    }

    public static Review from(
            Order order,
            int rating,
            String description
    ) {
        return new Review(
                order,
                rating,
                description
        );
    }
}
