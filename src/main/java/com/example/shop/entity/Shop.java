package com.example.shop.entity;

import com.example.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "shops")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    // 오픈 시간
    @Column(nullable = false)
    private String openedAt;

    // 마감 시간
    @Column(nullable = false)
    private String closedAt;

    // 최소 주문 금액
    @Column(nullable = false)
    private double minOrderPrice;

    // 생성자
    public Shop(User user, String name, String openedAt, String closedAt, double minOrderPrice) {
        this.user = user;
        this.name = name;
        this.openedAt = openedAt;
        this.closedAt = closedAt;
        this.minOrderPrice = minOrderPrice;
    }

    // 가게 정보 업데이트
    public void updateDetails(String name, String openedAt, String closedAt, double minOrderPrice) {
        if (name != null) this.name = name;
        if (openedAt != null) this.openedAt = openedAt;
        if (closedAt != null) this.closedAt = closedAt;
        if (minOrderPrice >= 0) this.minOrderPrice = minOrderPrice;
    }

    // 가게를 삭제된 상태로 마킹 (논리적 삭제)
    public void markAsDeleted() {
        this.isDeleted = true;
    }
}
