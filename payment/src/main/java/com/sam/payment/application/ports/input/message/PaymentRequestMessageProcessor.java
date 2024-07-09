package com.sam.payment.application.ports.input.message;

import com.sam.payment.application.dto.message.PaymentRequestMessage;

public interface PaymentRequestMessageProcessor {
    void processPayment(PaymentRequestMessage message);
}
