package com.example.shop.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ShopCreateRequestDto(
        // 가게 이름
        @NotBlank(message = "가게 이름은 필수입니다.")
        @Size(max = 10, message = "가게 이름은 최대 10자까지 가능합니다.")
        String name,

        // 오픈 시간
        @NotBlank(message = "오픈 시간 설정은 필수입니다.")
        String openedAt,

        // 마감 시간
        @NotBlank(message = "마감 시간 설정은 필수입니다.")
        String closedAt,

        // 최소 주문 금액
        @NotBlank(message = "최소 주문 금액은 필수입니다.")
        double minOrderPrice
) {}
