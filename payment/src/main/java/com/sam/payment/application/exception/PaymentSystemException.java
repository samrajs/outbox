package com.sam.payment.application.exception;

public class PaymentSystemException extends RuntimeException {
    public PaymentSystemException(String message) {
        super(message);
    }

    public PaymentSystemException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
