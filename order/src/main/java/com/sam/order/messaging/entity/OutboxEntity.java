package com.sam.order.messaging.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "outbox")
@AllArgsConstructor @NoArgsConstructor @Builder
@Data
public class OutboxEntity {
    public static final int VARCHAR_MAX_LENGTH = 4096;

    @Id
    private UUID id;
    
    @Column(nullable = false, length = VARCHAR_MAX_LENGTH)
    private String payload;

    @Column(nullable = false)
    private String aggregateType;

    @Column
    private UUID aggregateId;

    @Column(name = "tracingspancontext")
    private String tracingSpanContext;
}