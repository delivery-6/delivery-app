package com.example.order.entity;

public enum OrderState {
    NONE(0),
    ORDERED(1),
    REJECTED(2),
    APPROVED(3),
    DELIVERING(4),
    DELIVERED(5),
    CANCELED(99);

    public final int id;

    OrderState(int value) {
        this.id = value;
    }

    public static OrderState of(int id) {
        for (OrderState type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        //TODO: GlobalExceptionHandler 구현 후 수정예정입니다.
        throw new IllegalArgumentException("No OrderState with id: " + id);
    }

    public static boolean isUpdatable(OrderState from, OrderState to) {
        if(from == OrderState.REJECTED || from == OrderState.CANCELED)
            return false;
        return from.id < to.id;
    }
}