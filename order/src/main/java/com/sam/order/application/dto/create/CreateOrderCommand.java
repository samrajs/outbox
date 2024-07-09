package com.sam.order.application.dto.create;

import java.util.List;

import java.math.BigDecimal;
import java.util.UUID;

import com.sam.order.application.dto.common.OrderAddress;
import com.sam.order.application.dto.common.OrderItem;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateOrderCommand {
    @NotNull
    private final UUID customerId;
    @NotNull
    private final BigDecimal price;
    @NotNull
    private final List<OrderItem> items;
    @NotNull
    private final OrderAddress address;

}
