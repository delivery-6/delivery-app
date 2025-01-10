package com.example.shop.dto.response;

import com.example.shop.entity.Shop;

public record ShopCreateResponseDto(
        int id,
        String name,
        String message
) {
    // Shop 객체를 DTO 로 변환
    public static ShopCreateResponseDto from(Shop shop) {
        return new ShopCreateResponseDto(
                shop.getId(),
                shop.getName(),
                "가게가 성공적으로 생성되었습니다."
        );
    }
}
