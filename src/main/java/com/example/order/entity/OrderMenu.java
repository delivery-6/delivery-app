package com.example.order.entity;

import com.example.menu.entity.Menu;
import jakarta.persistence.*;
import org.aspectj.weaver.ast.Or;

import java.util.UUID;

@Entity
public class OrderMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

}
