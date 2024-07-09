package com.sam.order.controller.rest;


import com.sam.order.application.dto.create.CreateOrderCommand;
import com.sam.order.application.dto.create.CreateOrderResponse;
import com.sam.order.application.ports.input.service.OrderApplicationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(
        name="CRUD REST APIs for orders",
        description="CRUD REST APIs for create, update, delete order details"
)
@RestController
@RequestMapping(path="/api/v1/order", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class OrderController {
    OrderApplicationService service;

    @Operation(
        summary = "Create order REST API",
        description = "REST API for order creation"

    )
    @ApiResponse(
        responseCode = "201",
        description = "HTTP Status OK"
    )
    @PutMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@Valid @RequestBody CreateOrderCommand command) {
        log.info( "CreateOrderCommand received : {}", command);
        CreateOrderResponse response = service.createOrder(command);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}
