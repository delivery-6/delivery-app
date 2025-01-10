package com.example.order.entity;

import com.example.menu.entity.Menu;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(nullable = false)
    private int count;

    public OrderMenu(Order order, Menu menu, int count) {
        this.order = order;
        this.menu = menu;
        this.count = count;
    }
}
