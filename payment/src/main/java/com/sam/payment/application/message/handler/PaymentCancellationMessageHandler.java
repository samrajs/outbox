package com.sam.payment.application.message.handler;


import org.springframework.stereotype.Component;

import com.sam.payment.application.dto.message.PaymentCancellationMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentCancellationMessageHandler {

    @Transactional
    public void handle( PaymentCancellationMessage message ) {
        log.info( "Payment cancelled for the order {} for the customer {}", message.getOrderId(), message.getCustomerId() );
    }
}