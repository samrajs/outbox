package com.sam.payment.messaging.mapping;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sam.payment.application.dto.message.PaymentCancellationMessage;
import com.sam.payment.application.dto.message.PaymentRequestMessage;
import com.sam.payment.application.exception.PaymentSystemException;
import com.sam.payment.messaging.dto.OrderPayload;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentRequestDataMapper {
    private final ObjectMapper objectMapper;

    public PaymentRequestMessage toPaymentRequest( OrderPayload payload ) {
        return PaymentRequestMessage.builder()
                    .customerId( UUID.fromString( payload.getCustomerId() ) )
                    .orderId( UUID.fromString( payload.getId() ) )
                    .orderAmount( payload.getOrderAmount() )
                    .build();
    }

    public PaymentCancellationMessage toPaymentCancellation( OrderPayload payload ) {
        return PaymentCancellationMessage.builder()
                    .customerId( UUID.fromString( payload.getCustomerId() ) )
                    .orderId( UUID.fromString( payload.getId() ) )
                    .orderAmount( payload.getOrderAmount() )
                    .reason( payload.getReason() )
                    .build();
    }

    public OrderPayload toOrderPayload( String payload ) {
        log.info( "Payload : {}", payload );
        return Try.of( () -> objectMapper.readValue(payload, OrderPayload.class))
            .getOrElseThrow(t -> new PaymentSystemException("Error in converting to OrderPayload", t));
    }

}
