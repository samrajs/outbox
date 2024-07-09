package com.sam.order.application.ports.output.repository;

import java.util.Optional;

import com.sam.order.domain.entity.Order;
import com.sam.order.domain.valueobject.OrderId;

public interface OrderRepository {
    Optional<Order> findById( OrderId id );
    Order save( Order order );
}
