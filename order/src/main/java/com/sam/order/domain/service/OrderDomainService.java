package com.sam.order.domain.service;

import com.sam.order.domain.entity.Order;
import com.sam.order.domain.event.OrderCreated;

public interface OrderDomainService {
    OrderCreated validateAndInit( Order order );
}
