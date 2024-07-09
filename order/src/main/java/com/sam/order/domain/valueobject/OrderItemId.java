package com.sam.order.domain.valueobject;

public class OrderItemId extends BaseId<Long> {
    public OrderItemId(Long value) {
        super(value);
    }

    public static OrderItemId from( Long value ) {
        return new OrderItemId(value);
    }
}