package com.sam.order.domain.service.impl;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.sam.order.domain.entity.Order;
import com.sam.order.domain.event.OrderCreated;
import com.sam.order.domain.service.OrderDomainService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {
    private final String UTC = "UTC";

    @Override
    public OrderCreated validateAndInit(Order order) {
        order.validate();
        order.initOrder();
        log.info( "Order {} validated and created", order );
        return new OrderCreated(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

}
