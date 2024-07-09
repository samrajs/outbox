package com.sam.payment.application.dto.message;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentRequestMessage {
    @NotNull
    private UUID orderId;
    @NotNull
    private UUID customerId;
    @NotNull
    private BigDecimal orderAmount;
    private String reason;
}