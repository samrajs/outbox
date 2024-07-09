package com.sam.payment.application.message.handler;

import org.springframework.stereotype.Component;

import com.sam.payment.application.dto.message.PaymentRequestMessage;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentRequestMessageHandler {
    @Transactional
    public void handle( PaymentRequestMessage message ) {
        log.info( "Payment successful for the order {} for the customer {}", message.getOrderId(), message.getCustomerId() );
    }
}