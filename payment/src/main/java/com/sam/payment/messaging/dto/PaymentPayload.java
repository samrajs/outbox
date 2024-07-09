package com.sam.payment.messaging.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class PaymentPayload {
    @JsonProperty
    private String id;
    @JsonProperty
    private String orderId;
    @JsonProperty
    private String customerId;
    @JsonProperty
    private BigDecimal orderAmount;
    @JsonProperty
    private String status;
}