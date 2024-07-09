package com.sam.order.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class StreetAddress {
    private final UUID id;
    private final String street;
    private final String postalCode;
    private final String city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StreetAddress that = (StreetAddress) o;
        return street.equals(that.street) && postalCode.equals(that.postalCode) && city.equals(that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, postalCode, city);
    }
}
