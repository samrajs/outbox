package com.sam.order.dataaccess.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sam.order.dataaccess.entity.OrderEntity;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {

}
