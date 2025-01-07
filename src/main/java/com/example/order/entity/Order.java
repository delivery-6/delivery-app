package com.example.order.entity;
import com.example.User.Entity.User;
import com.example.menu.entity.Menu;
import com.example.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Table(name = "orders")
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

    @Setter
    @Enumerated(EnumType.STRING)
    private OrderState state;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


    public enum OrderState {
       NONE,ORDERED,APPROVED,DELIVERING, DELIVERED, REJECTED, CANCELED
    }
}