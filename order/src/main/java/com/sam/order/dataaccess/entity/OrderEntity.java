package com.sam.order.dataaccess.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.sam.order.domain.valueobject.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "orders")
@Entity
public class OrderEntity extends AuditableEntity {
    @Id
    private UUID id;
    private UUID customerId;
    private BigDecimal orderAmount;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus status;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private OrderAddressEntity address;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemEntity> items;

        @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
