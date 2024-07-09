package com.sam.order.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sam.order.domain.valueobject.OrderId;
import com.sam.order.domain.valueobject.OrderStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrderAlreadyInitialized extends RuntimeException {
    public OrderAlreadyInitialized( OrderId orderId, OrderStatus status ) {
        super(String.format( "Order already contains either order id (%s) or order status (%s)", orderId.getValue(), status ) );
    }
}