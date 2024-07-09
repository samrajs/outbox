package com.sam.order.domain.entity;

import com.sam.order.domain.valueobject.Money;
import com.sam.order.domain.valueobject.ProductId;
import com.sam.order.domain.valueobject.ProductStatus;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Product extends BaseEntity<ProductId> {
    private final String name;
    private final Money price;
    private final ProductStatus status;
}
