package com.sam.order.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sam.order.domain.valueobject.Money;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrderPriceInvalid extends RuntimeException {
    public OrderPriceInvalid( Money price ) {
        super(String.format( "Order total price (%s) is null or less than 0 ", price.getAmount() ) );
    }
}