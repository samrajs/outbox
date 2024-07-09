package com.sam.order.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sam.order.domain.valueobject.OrderStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrderStatusInvalid extends RuntimeException {
    public OrderStatusInvalid( OrderStatus currentStatus, OrderStatus nextStatus ) {
        super(String.format( "Order status not allowed to move from %s to %s", currentStatus, nextStatus ) );
    }
}