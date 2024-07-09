package com.sam.order.domain.event;

@SuppressWarnings("rawtypes")
public interface DomainEventPublisher<T extends DomainEvent> {
    void publish(T domainEvent);
}
