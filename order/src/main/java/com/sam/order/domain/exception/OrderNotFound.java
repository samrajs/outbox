package com.sam.order.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sam.order.domain.valueobject.OrderId;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFound extends RuntimeException {
    public OrderNotFound( OrderId orderId ) {
        super(String.format( "Order with order id (%s) is not found", orderId ) );
    }
}