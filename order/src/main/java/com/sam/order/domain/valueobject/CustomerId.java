package com.sam.order.domain.valueobject;

import java.util.UUID;


public class CustomerId extends BaseId<UUID> {
    public CustomerId(UUID value) {
        super(value);
    }

    public static CustomerId from( UUID id ) {
        return new CustomerId(id);
    }
}