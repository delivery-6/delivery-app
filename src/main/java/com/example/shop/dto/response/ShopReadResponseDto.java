package com.example.shop.dto.response;

import com.example.shop.entity.Shop;
import com.example.menu.dto.response.MenuResponseDetailDto;

import java.util.List;

public record ShopReadResponseDto(
        int id,
        String name,
        boolean isDeleted,
        List<MenuResponseDetailDto> menuList  // 메뉴 목록
) {
    // Shop 객체를 DTO 로 변환, 삭제된 가게는 메뉴가 없음
    public static ShopReadResponseDto from(Shop shop) {
        return new ShopReadResponseDto(
                shop.getId(),
                shop.getName(),
                shop.getIsDeleted(),
                shop.getIsDeleted() ? null : getMenuList(shop.getId())  // 삭제된 가게는 메뉴가 없음
        );
    }

    // 메뉴 목록 가져오기 (가게 단건 조회 시에만 포함)
    private static List<MenuResponseDetailDto> getMenuList(int shopId) {
        // 메뉴 목록을 조회하는 로직 추가 (Menu 엔티티에서 조회)
        return List.of();  // 예시로 빈 목록 반환
    }
}
