package com.sam.order.application.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
public class OrderItem {
    @NotNull
    private final UUID productId;
    @NotNull
    private final Integer quantity;
    @NotNull
    private final BigDecimal price;
}
