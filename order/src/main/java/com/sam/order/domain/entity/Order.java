package com.sam.order.domain.entity;

import java.util.List;
import java.util.UUID;

import com.sam.order.domain.exception.OrderAlreadyInitialized;
import com.sam.order.domain.exception.OrderItemPriceInvalid;
import com.sam.order.domain.exception.OrderPriceInvalid;
import com.sam.order.domain.exception.OrderStatusInvalid;
import com.sam.order.domain.valueobject.CustomerId;
import com.sam.order.domain.valueobject.Money;
import com.sam.order.domain.valueobject.OrderId;
import com.sam.order.domain.valueobject.OrderItemId;
import com.sam.order.domain.valueobject.OrderStatus;
import com.sam.order.domain.valueobject.StreetAddress;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Order extends AggregateRoot<OrderId> {
    private final CustomerId customerId;
    private final StreetAddress deliveryAddress;
    private final Money orderAmount;
    private OrderStatus status;

    private final List<OrderItem> items;

    public void pay() {
        if ( status != OrderStatus.CREATED ) {
            throw new OrderStatusInvalid(status, OrderStatus.PAID);
        }
        status = OrderStatus.PAID;
    }

    public void cancel() {
        if ( status != OrderStatus.CREATED ) {
            throw new OrderStatusInvalid(status, OrderStatus.CANCELLED);
        }
        status = OrderStatus.CANCELLED;
    }

    public void declinePayment() {
        if ( status != OrderStatus.CREATED ) {
            throw new OrderStatusInvalid(status, OrderStatus.PAYMENT_DECLINED);
        }
        status = OrderStatus.PAYMENT_DECLINED;
    }

    public void initOrder() {
        setId( new OrderId( UUID.randomUUID() ) );
        status = OrderStatus.CREATED;
        initOrderItems();
    }

    public void validate() {
        validateInitialOrder();
        validateTotalPrice();
        validateItemPrice();
    }

    private void validateInitialOrder() {
        if ( status != null || getId() != null ) {
            throw new OrderAlreadyInitialized( getId(), status );
        }
    }

    private void validateTotalPrice() {
        if ( orderAmount == null || !orderAmount.isGreaterThanZero() ) {
            throw new OrderPriceInvalid(orderAmount);
        }
    }

    private void validateItemPrice() {
        Money itemsTotal = items.stream().map( orderItem -> orderItem.getPrice() ).reduce(Money.ZERO, Money::add);

        if (!orderAmount.equals(itemsTotal)) {
            throw new OrderItemPriceInvalid(orderAmount, itemsTotal);

        }
    }

    private void initOrderItems() {
        long itemId = 1;
        for (OrderItem orderItem : items) {
            orderItem.initOrderItem(getId(), new OrderItemId(itemId++));
        }
    }

}
