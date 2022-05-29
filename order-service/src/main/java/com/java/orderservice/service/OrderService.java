package com.java.orderservice.service;

import com.java.orderservice.dto.OrderRequestDto;
import com.java.orderservice.dto.OrderResonseDTO;

public interface OrderService {
    OrderResonseDTO placeOrder(OrderRequestDto orderRequestDto);
}
