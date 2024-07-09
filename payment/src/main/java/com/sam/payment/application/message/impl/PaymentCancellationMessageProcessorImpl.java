package com.sam.payment.application.message.impl;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.sam.payment.application.dto.message.PaymentCancellationMessage;
import com.sam.payment.application.message.handler.PaymentCancellationMessageHandler;
import com.sam.payment.application.ports.input.message.PaymentCancellationMessageProcessor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Validated
public class PaymentCancellationMessageProcessorImpl implements PaymentCancellationMessageProcessor {
    private final PaymentCancellationMessageHandler handler;

    @Override
    public void processPaymentCancellation(PaymentCancellationMessage message) {
        log.info( "Payment has been received: {}", message );
        handler.handle( message );
    }
}
