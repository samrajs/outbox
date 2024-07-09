package com.sam.order.application.exception;

public class OrderSystemException extends RuntimeException {
    public OrderSystemException(String message) {
        super(message);
    }

    public OrderSystemException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
