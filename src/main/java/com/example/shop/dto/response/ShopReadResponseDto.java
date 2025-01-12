package com.example.shop.dto.response;

import com.example.menu.dto.response.MenuResponseDetailDto;
import com.example.menu.entity.Menu;
import com.example.menu.repository.MenuRepository;
import com.example.shop.entity.Shop;

import java.util.List;
import java.util.stream.Collectors;

public record ShopReadResponseDto(
        int id,
        String name,
        boolean isDeleted,
        List<MenuResponseDetailDto> menuList  // 메뉴 목록
) {
    public static ShopReadResponseDto from(Shop shop, MenuRepository menuRepository) {
        List<MenuResponseDetailDto> menuList = menuRepository.findByShopId(shop.getId()).stream()
                .map(MenuResponseDetailDto::from)
                .collect(Collectors.toList());

        return new ShopReadResponseDto(
                shop.getId(),
                shop.getName(),
                shop.getIsDeleted(),
                shop.getIsDeleted() ? null : menuList
        );
    }
}
