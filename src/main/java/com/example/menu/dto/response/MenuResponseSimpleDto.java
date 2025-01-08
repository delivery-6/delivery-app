package com.example.menu.dto.response;

import com.example.menu.entity.Menu;

public record MenuResponseSimpleDto(
        String name,
        int price
) {
    public static MenuResponseSimpleDto from(Menu menu) {
        return new MenuResponseSimpleDto(menu.getName(), menu.getPrice());
    }
}