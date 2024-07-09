package com.sam.payment.application.message.impl;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.sam.payment.application.dto.message.PaymentRequestMessage;
import com.sam.payment.application.message.handler.PaymentRequestMessageHandler;
import com.sam.payment.application.ports.input.message.PaymentRequestMessageProcessor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Validated
public class PaymentRequestMessageProcessorImpl implements PaymentRequestMessageProcessor {
    private final PaymentRequestMessageHandler handler;

    @Override
    public void processPayment(PaymentRequestMessage message) {
        log.info( "Payment has been received: {}", message );
        handler.handle( message );
    }
}
