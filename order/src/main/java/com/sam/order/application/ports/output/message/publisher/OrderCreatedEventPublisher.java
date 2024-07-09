package com.sam.order.application.ports.output.message.publisher;

import com.sam.order.domain.event.DomainEventPublisher;
import com.sam.order.domain.event.OrderCreated;

public interface OrderCreatedEventPublisher extends DomainEventPublisher<OrderCreated> {

}