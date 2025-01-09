package com.example.order.entity;

import com.example.menu.entity.Menu;
import com.example.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Table(name = "orders")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderMenu> orderMenus = List.of();

    @Enumerated(EnumType.STRING)
    private OrderState state = OrderState.NONE;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Order(User user) {
        this.user = user;
        this.state = OrderState.NONE;
    }

    public void updateState(OrderState state) {
        if (OrderState.isUpdatable(this.state, state))
            //TODO: GlobalExceptionHandler 구현 후 수정예정입니다.
            throw new IllegalStateException("OrderState cannot be update from " + this.state + " to " + state);
        this.state = state;
    }

    public static Order from(
            Map<Menu, Integer> menuInfo,
            User user
    ) {
        Order order = new Order(user);
        order.registerMenus(menuInfo);
        return order;
    }

    private void registerMenus(Map<Menu, Integer> menuInfo) {
        menuInfo.forEach((menu, quantity) ->
                this.orderMenus.add(new OrderMenu(this, menu, quantity))
        );
    }
}