package com.sam.order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sam.order.domain.service.OrderDomainService;
import com.sam.order.domain.service.impl.OrderDomainServiceImpl;

@Configuration
public class BeanConfiguration {
    @Bean
    public OrderDomainService orderDomainService() {
        return new OrderDomainServiceImpl();
    }
}
