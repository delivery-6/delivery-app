package com.example.entity;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @ManyToMany
    @JoinTable(
            name = "order_menu", // 중간 테이블 이름
            joinColumns = @JoinColumn(name = "order_id"), // 현재 엔티티(Order)의 외래 키
            inverseJoinColumns = @JoinColumn(name = "menu_id") // 대상 엔티티(Menu)의 외래 키
    )
    private List<Menu> menus;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


    public enum OrderState {
        PENDING, COMPLETED, CANCELLED
    }
}