package com.example.shop.dto.response;

import com.example.shop.entity.Shop;

public record ShopUpdateResponseDto(
        int id,
        String name,
        String message
) {
    // Shop 객체를 DTO 로 변환
    public static ShopUpdateResponseDto from(Shop shop) {
        return new ShopUpdateResponseDto(
                shop.getId(),
                shop.getName(),
                "가게 정보가 성공적으로 수정되었습니다."
        );
    }
}
