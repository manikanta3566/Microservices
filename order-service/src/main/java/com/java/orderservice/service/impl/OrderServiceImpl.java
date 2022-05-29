package com.java.orderservice.service.impl;

import com.java.orderservice.dto.OrderItemsDTO;
import com.java.orderservice.dto.OrderRequestDto;
import com.java.orderservice.dto.OrderResonseDTO;
import com.java.orderservice.entity.Order;
import com.java.orderservice.entity.OrderItems;
import com.java.orderservice.repository.OrderRepository;
import com.java.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Override
    public OrderResonseDTO placeOrder(OrderRequestDto orderRequestDto) {
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderItems(orderRequestDto.getOrderItemsDTO().stream().map(orderItems ->new OrderItems(orderItems.getItemName(),orderItems.getPrice(),orderItems.getQuantity(),order)).collect(Collectors.toList()));
        Order savedOrder = orderRepository.save(order);
      OrderResonseDTO orderResonseDTO=new OrderResonseDTO();
      orderResonseDTO.setOrderNumber(savedOrder.getOrderNumber());
      orderResonseDTO.setId(savedOrder.getId());
      orderResonseDTO.setOrderItemsDto(savedOrder.getOrderItems().stream().map(orderItems ->new OrderItemsDTO(orderItems.getItemName(),orderItems.getPrice(),orderItems.getQuantity())).collect(Collectors.toList()));
   return orderResonseDTO;
    }
}
