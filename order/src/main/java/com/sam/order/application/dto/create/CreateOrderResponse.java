package com.sam.order.application.dto.create;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateOrderResponse {
    @NotNull
    private UUID orderId;
    @NotNull
    private String status;
}
