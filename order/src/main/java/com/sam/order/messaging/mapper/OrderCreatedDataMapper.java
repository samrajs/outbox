package com.sam.order.messaging.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sam.order.domain.event.OrderCreated;
import com.sam.order.messaging.dto.OrderPayload;
import com.sam.order.messaging.entity.OutboxEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCreatedDataMapper {
    private final ObjectMapper objectMapper;

    public OutboxEntity toOutboxEntity( OrderCreated event ) {
        return OutboxEntity.builder()
            .id( UUID.randomUUID() )
            .aggregateType( "order" )
            .aggregateId( event.getOrder().getId().getValue() )
            .payload( toJson( toOrderPayload( event ) ) )
            .build();
    }

    public OrderPayload toOrderPayload( OrderCreated event ) {
        return OrderPayload.builder()
                .id( event.getOrder().getId().getValue().toString() )
                .customerId( event.getOrder().getCustomerId().getValue().toString() )
                .orderAmount( event.getOrder().getOrderAmount().getAmount() )
                .status( "CREATED" )
                .build();
    }

    /*public OrderCancelledAvroModel orderCancelledEventTOrderCancelledAvroModel( OrderCancelled event ) {
        return OrderCancelledAvroModel.newBuilder()
                 .setId( event.getOrder().getId().getValue().toString() )
                 .setCustomerId( event.getOrder().getCustomerId().getValue().toString() )
                 .setPrice( event.getOrder().getOrderAmount().getAmount() )
                 .setCreatedAt( event.getCreateAt().toInstant() )
                 .build();
    }

    public PaymentDeclinedMessage paymentDeclinedAvroModelToPaymentDeclinedMessage( PaymentDeclinedAvroModel paymentDeclinedAvroModel ) {
        return PaymentDeclinedMessage.builder()
                .orderId( UUID.fromString( paymentDeclinedAvroModel.getId() ) )
                .orderAmount( paymentDeclinedAvroModel.getPrice() )
                .reason( paymentDeclinedAvroModel.getReason() )
                .build();
    }*/

    private String toJson( OrderPayload payload ) {
        try {
            return objectMapper.writeValueAsString( payload );
        } catch (JsonProcessingException e) {
            log.error("Could not create OrderCreatedPayload object for order id: {}",
                    payload.getId(), e);
            throw new RuntimeException("Could not create OrderCreatedPayload object for order id: " +
            payload.getId(), e);
        }
    }

}