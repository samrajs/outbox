package com.sam.order.dataaccess.adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.sam.order.application.ports.output.repository.OrderRepository;
import com.sam.order.dataaccess.mapper.OrderDataAccessMapper;
import com.sam.order.dataaccess.repository.OrderJpaRepository;
import com.sam.order.domain.entity.Order;
import com.sam.order.domain.valueobject.OrderId;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository{
    private final OrderDataAccessMapper mapper;
    private final OrderJpaRepository jpaRepository;

    @Override
    public Optional<Order> findById(OrderId id) {
        return jpaRepository.findById( id.getValue() ).map( mapper::toOrder );
    }

    @Override
    public Order save(Order order) {
        return mapper.toOrder( jpaRepository.save( mapper.toOrderEntity(order)) ) ;
    }

}
