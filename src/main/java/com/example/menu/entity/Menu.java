package com.example.menu.entity;

import com.example.menu.dto.request.MenuCreateRequestDto;
import com.example.menu.dto.request.MenuUpdateRequestDto;
import com.example.shop.entity.Shop;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Shop shop;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    public Menu(Shop shop, String name, Integer price) {
        this.shop = shop;
        this.name = name;
        this.price = price;
    }

    public Menu partialUpdate(MenuUpdateRequestDto dto) {
        this.name = dto.name();
        this.price = dto.price();
        return this;
    }

    //DTO 를 Entity 로 바꾸기 위한 Util method
    public static Menu from(Shop shop, MenuCreateRequestDto dto){
        return new Menu(
                shop,
                dto.name(),
                dto.price()
        );
    }
}