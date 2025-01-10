package com.example.order.dto.response;

import com.example.order.entity.Order;

import java.util.Map;
import java.util.stream.Collectors;

public record OrderResponseDto(
        int id,
        String username,
        String shopName,
        Map<String, Integer> menuInfo,
        int totalPrice
) {
    public static OrderResponseDto from(Order order) {
        return new OrderResponseDto(
                order.getId(),
                order.getUser().getName(),
                //TODO: GlobalExceptionHandler 구현 후 수정예정입니다.
                order.getOrderMenus().stream().findFirst().orElseThrow(() -> new IllegalArgumentException("")).getMenu().getShop().getName(),
                order.getOrderMenus().stream()
                        .collect(Collectors.toMap(
                                orderMenu -> orderMenu.getMenu().getName(),
                                orderMenu -> orderMenu.getMenu().getPrice()
                        )),
                order.getOrderMenus().stream().mapToInt(item -> item.getMenu().getPrice()).sum()
        );
    }
}
