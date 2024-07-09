package com.sam.order.dataaccess.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sam.order.dataaccess.entity.OrderAddressEntity;
import com.sam.order.dataaccess.entity.OrderEntity;
import com.sam.order.dataaccess.entity.OrderItemEntity;
import com.sam.order.domain.entity.Order;
import com.sam.order.domain.entity.OrderItem;
import com.sam.order.domain.entity.Product;
import com.sam.order.domain.valueobject.CustomerId;
import com.sam.order.domain.valueobject.Money;
import com.sam.order.domain.valueobject.OrderId;
import com.sam.order.domain.valueobject.OrderItemId;
import com.sam.order.domain.valueobject.ProductId;
import com.sam.order.domain.valueobject.StreetAddress;

@Component
public class OrderDataAccessMapper {
    public OrderEntity toOrderEntity( Order order ) {
        OrderEntity orderEntity =  OrderEntity.builder()
                 .id( order.getId().getValue() )
                 .customerId( order.getCustomerId().getValue() )
                 .orderAmount( order.getOrderAmount().getAmount() )
                 .status( order.getStatus() )
                 .items( toOrderItemEntities( order.getItems() ))
                 .address( toOrderAddressEntity( order.getDeliveryAddress() ) )
                 .build();

        orderEntity.getAddress().setOrder(orderEntity);

        orderEntity.getItems().forEach( item -> item.setOrder(orderEntity) );

        return orderEntity;
    }

    public List<OrderItemEntity> toOrderItemEntities( List<OrderItem> orderItems ) {
        return orderItems.stream().map( orderitem -> OrderItemEntity.builder()
                                                         .id( orderitem.getId().getValue() )
                                                         .price( orderitem.getPrice().getAmount() )
                                                         .productId( orderitem.getProduct().getId().getValue() )
                                                         .quantity( orderitem.getQuantity() ).build() )
                    .collect( Collectors.toList() );
                                                         
    }

    public OrderAddressEntity toOrderAddressEntity( StreetAddress streetAddress ) {
        return OrderAddressEntity.builder()
                   .id( streetAddress.getId())
                   .street( streetAddress.getStreet() )
                   .city( streetAddress.getCity() )
                   .postalCode( streetAddress.getPostalCode() )
                   .build();
    }

    public Order toOrder( OrderEntity orderEntity ) {
        return Order.builder()
                 .id( OrderId.from( orderEntity.getId() ) )
                 .customerId( CustomerId.from( orderEntity.getCustomerId() ))
                 .orderAmount( new Money( orderEntity.getOrderAmount() ) )
                 .status( orderEntity.getStatus() )
                 .items( toOrderItems( orderEntity.getItems() ))
                 .deliveryAddress( toStreetAddress( orderEntity.getAddress() ) )
                 .build();
    }

    public List<OrderItem> toOrderItems( List<OrderItemEntity> orderItemEntities ) {
        return orderItemEntities.stream().map( orderItemEntity -> OrderItem.builder()
                                                   .id( OrderItemId.from( orderItemEntity.getId() ))
                                                   .price( new Money( orderItemEntity.getPrice() ))
                                                   .quantity( orderItemEntity.getQuantity() )
                                                   .product( Product.builder().id( ProductId.from (orderItemEntity.getProductId())).build() ) 
                                                   .build())
                                                   .collect( Collectors.toList() );
    }

    public StreetAddress toStreetAddress( OrderAddressEntity addressEntity ) {
        return StreetAddress.builder()
            .id( addressEntity.getId() )
              .street( addressEntity.getStreet() )
              .city( addressEntity.getCity() )
              .postalCode( addressEntity.getPostalCode() )
              .build();
    }
}
