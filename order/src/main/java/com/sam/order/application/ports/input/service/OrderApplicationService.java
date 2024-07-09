package com.sam.order.application.ports.input.service;

import com.sam.order.application.dto.create.CreateOrderCommand;
import com.sam.order.application.dto.create.CreateOrderResponse;


public interface OrderApplicationService {
    CreateOrderResponse createOrder( CreateOrderCommand command);
}
