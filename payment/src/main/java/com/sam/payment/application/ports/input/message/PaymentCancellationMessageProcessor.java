package com.sam.payment.application.ports.input.message;

import com.sam.payment.application.dto.message.PaymentCancellationMessage;

public interface PaymentCancellationMessageProcessor {
    void processPaymentCancellation(PaymentCancellationMessage message);
}
