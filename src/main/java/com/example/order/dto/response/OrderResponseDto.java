package com.example.order.dto.response;

import com.example.exception.CustomException;
import com.example.exception.ErrorCode;
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
                order.getOrderMenus().stream().findFirst()
                        .orElseThrow(() -> CustomException.of(ErrorCode.BAD_REQUEST, "Cannot found any menu in this Order with ID: " + order.getId()))
                        .getMenu()
                        .getShop()
                        .getName(),
                order.getOrderMenus().stream()
                        .collect(Collectors.toMap(
                                orderMenu -> orderMenu.getMenu().getName(),
                                orderMenu -> orderMenu.getMenu().getPrice()
                        )),
                order.getOrderMenus().stream().mapToInt(item -> item.getMenu().getPrice()).sum()
        );
    }
}
