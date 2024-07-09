package com.sam.order.domain.entity;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public abstract class AggregateRoot<ID> extends BaseEntity<ID> {
}

