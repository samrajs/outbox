package com.sam.order.application.service.handler;

import org.springframework.stereotype.Component;

import com.sam.order.application.dto.create.CreateOrderCommand;
import com.sam.order.application.dto.create.CreateOrderResponse;
import com.sam.order.application.mapper.OrderDataMapper;
import com.sam.order.application.ports.output.message.publisher.OrderCreatedEventPublisher;
import com.sam.order.application.ports.output.repository.OrderRepository;
import com.sam.order.domain.entity.Order;
import com.sam.order.domain.event.OrderCreated;
import com.sam.order.domain.service.OrderDomainService;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateOrderCommandHandler {
    private final OrderRepository repository;
    private final OrderDataMapper mapper;
    private final OrderDomainService domainService;
    private final OrderCreatedEventPublisher publisher;

    //@WithSpan(kind = SpanKind.SERVER)
    @Transactional
    public CreateOrderResponse createOrder( CreateOrderCommand command ) {
        Order order = mapper.createOrderCommandToOrder(command);

        log.info( "Converted order : {}", order );
        OrderCreated event = domainService.validateAndInit(order);
        repository.save(order);
        log.info( "Order created with id : {}", event.getOrder().getId().getValue() );
        publisher.publish(event);
        log.info( "OrderCreated event published for the order id : {}", event.getOrder().getId().getValue());
        return mapper.orderToCreateOrderResponse(order);
    }

}
