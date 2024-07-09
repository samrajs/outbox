package com.sam.order.domain.valueobject;

import java.util.Objects;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
//@ToString
public abstract class BaseId<T> {
    private final T value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseId<?> baseId = (BaseId<?>) o;
        return value.equals(baseId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public String toString() {
        return value != null?value.toString():null;
    }
}