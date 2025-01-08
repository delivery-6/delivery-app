package com.example.menu.dto.response;

import com.example.menu.entity.Menu;
import java.time.LocalDateTime;

public record MenuResponseDetailDto(
        int id,
        int shopId,
        String name,
        int price,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static MenuResponseDetailDto from(Menu menu) {
        return new MenuResponseDetailDto(
                menu.getId(),
                menu.getShop().getId(),
                menu.getName(),
                menu.getPrice(),
                menu.getCreatedAt(),
                menu.getUpdatedAt());
    }
}
