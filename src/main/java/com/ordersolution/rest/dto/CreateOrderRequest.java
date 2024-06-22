package com.ordersolution.rest.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateOrderRequest {

    private BigDecimal orderSum;

    private Long customerId;
}