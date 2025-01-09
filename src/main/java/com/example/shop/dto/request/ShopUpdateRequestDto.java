package com.example.shop.dto.request;

import jakarta.validation.constraints.Size;

public record ShopUpdateRequestDto(
        @Size(max = 10, message = "가게 이름은 최대 10자까지 가능합니다.")
        String name, // nullable 허용

        String openedAt,  // nullable 허용
        String closedAt,  // nullable 허용
        Double minOrderPrice // nullable 허용
) {}
