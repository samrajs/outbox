package com.sam.order.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sam.order.domain.valueobject.Money;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrderItemPriceInvalid extends RuntimeException {
    public OrderItemPriceInvalid( Money orderPrice, Money  itemTotal) {
        super(String.format( "Order price (%s) and item total price (%s) doesn't match", orderPrice.getAmount(), itemTotal.getAmount() ) );
    }
}