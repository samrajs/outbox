package com.sam.order.messaging.publisher.outbox;

import org.springframework.stereotype.Component;

import com.sam.order.application.ports.output.message.publisher.OrderCreatedEventPublisher;
import com.sam.order.domain.event.OrderCreated;
import com.sam.order.messaging.entity.OutboxEntity;
import com.sam.order.messaging.mapper.OrderCreatedDataMapper;
import com.sam.order.messaging.repository.OutboxRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderCreatedEventOutboxPublisher implements OrderCreatedEventPublisher {
    private final OrderCreatedDataMapper mapper;
    private final OutboxRepository repository;
    
    @Override
    public void publish(OrderCreated event) {
        String orderId = event.getOrder().getId().getValue().toString();
        log.info( "OrderCreated event to be published for {}", orderId );
        OutboxEntity outbox = mapper.toOutboxEntity(event);

        repository.save(outbox);
    }

}
