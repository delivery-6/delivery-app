package com.example.review.entity;

import com.example.order.entity.Order;
import com.example.review.dto.request.ReviewCreateRequestDto;
import com.example.review.dto.request.ReviewUpdateRequestDto;
import com.example.shop.entity.Shop;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Table(name = "reviews")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @Setter
    @Column(nullable = false)
    private Integer rating;

    @Setter
    @Column(length = 50)
    private String description;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Review(Order order, Shop shop, Integer rating, String description) {
        this.order = order;
        this.shop = shop;
        this.rating = rating;
        this.description = description;
    }

    public Review partialUpdate(ReviewUpdateRequestDto dto){
        this.rating = dto.rating();
        this.description = dto.description();
        return this;
    }


    public static Review from(Shop shop,Order order, ReviewCreateRequestDto dto) {
        return new Review(
                order,
                shop,
                dto.rating(),
                dto.description()
        );
    }
}
