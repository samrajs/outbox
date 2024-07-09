package com.sam.order.domain.entity;

import com.sam.order.domain.valueobject.Money;
import com.sam.order.domain.valueobject.OrderId;
import com.sam.order.domain.valueobject.OrderItemId;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class OrderItem extends BaseEntity<OrderItemId> {
    private OrderId orderId;
    private final int quantity;
    private final Money price;
    private final Product product;

    void initOrderItem( OrderId orderId, OrderItemId itemId ) {
        super.setId(itemId);
        this.orderId = orderId;
    }
}
