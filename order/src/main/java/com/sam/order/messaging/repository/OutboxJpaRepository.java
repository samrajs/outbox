package com.sam.order.messaging.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sam.order.messaging.entity.OutboxEntity;

@Repository
public interface OutboxJpaRepository extends JpaRepository<OutboxEntity, UUID> {
}
