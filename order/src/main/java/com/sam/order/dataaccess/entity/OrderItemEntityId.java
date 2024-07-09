package com.sam.order.dataaccess.entity;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEntityId {
    private Long id;
    private OrderEntity order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemEntityId that = (OrderItemEntityId) o;
        return id.equals(that.id) && order.equals(that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order);
    }

}
