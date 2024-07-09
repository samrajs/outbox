package com.sam.order.domain.event;

import java.time.ZonedDateTime;

import com.sam.order.domain.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderCreated implements DomainEvent<Order> {
    private final Order order;
    private final ZonedDateTime createAt;

}
