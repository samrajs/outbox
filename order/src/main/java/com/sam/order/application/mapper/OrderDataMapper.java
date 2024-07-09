package com.sam.order.application.mapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sam.order.application.dto.common.OrderAddress;
import com.sam.order.application.dto.common.OrderItem;
import com.sam.order.application.dto.create.CreateOrderCommand;
import com.sam.order.application.dto.create.CreateOrderResponse;
import com.sam.order.domain.entity.Order;
import com.sam.order.domain.entity.Product;
import com.sam.order.domain.valueobject.CustomerId;
import com.sam.order.domain.valueobject.Money;
import com.sam.order.domain.valueobject.ProductId;
import com.sam.order.domain.valueobject.StreetAddress;

@Component
public class OrderDataMapper {
    public Order createOrderCommandToOrder( CreateOrderCommand command ) {
        return Order.builder()
             .customerId( CustomerId.from(command.getCustomerId() ))
             .orderAmount( new Money( command.getPrice() ) )
             .items( orderItemsToOrderItemEntities( command.getItems() ) )
             .deliveryAddress( orderAddressToStreetAddress( command.getAddress() ))
             .build();
    }

    public List<com.sam.order.domain.entity.OrderItem> orderItemsToOrderItemEntities( List<OrderItem> items ) {
        return items.stream().map(item -> com.sam.order.domain.entity.OrderItem.builder()
                   .product( Product.builder().id(new ProductId( item.getProductId()) ).build() )
                   .price( new Money( item.getPrice() ) )
                   .quantity( item.getQuantity() )
                   .build()
              ).collect(Collectors.toList());
    }

    public CreateOrderResponse orderToCreateOrderResponse( Order order ) {
        return CreateOrderResponse.builder()
                 .orderId(order.getId().getValue())
                 .status( order.getStatus().name() )
                 .build();
    }

    public StreetAddress orderAddressToStreetAddress( OrderAddress address ) {
        return new StreetAddress( UUID.randomUUID(), address.getStreet(), address.getPostalCode(), address.getCity() );

    }

}