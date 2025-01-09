package com.example.shop.dto.request;

import jakarta.validation.constraints.Size;

public record ShopUpdateRequestDto(
        @Size(max = 10, message = "가게 이름은 최대 10자까지 가능합니다.")
        String name, // nullable 허용

        String openingHours,  // nullable 허용
        String closingHours,  // nullable 허용
        Double minOrderAmount // nullable 허용
) {}
