package com.java.orderservice.service.impl;

import com.java.orderservice.dto.*;
import com.java.orderservice.entity.Order;
import com.java.orderservice.entity.OrderItems;
import com.java.orderservice.repository.OrderRepository;
import com.java.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final WebClient.Builder webClient;

    @Override
    public String placeOrder(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderItems(orderRequestDto.getOrderItemsDTO().stream().map(orderItems -> new OrderItems(orderItems.getItemName(), orderItems.getPrice(), orderItems.getQuantity(), order)).collect(Collectors.toList()));
        List<String> itemNames = new ArrayList<>();
        order.getOrderItems().stream().forEach(orderItems -> itemNames.add(orderItems.getItemName()));
InventoryRequest inventoryRequest=new InventoryRequest();
inventoryRequest.setItemsList(itemNames);
        //calling inventory service for checking order is in stock or not
        InventoryResponse[] itemsInStocks = webClient.build().post()
                .uri("http://inventory-service/v0/api/inventory")
                .bodyValue(inventoryRequest)
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        boolean result = Arrays.stream(itemsInStocks).allMatch(InventoryResponse::isInStock);
        Order savedOrder;
        if (result) {
            savedOrder = orderRepository.save(order);
        } else {
            throw new RuntimeException("out of stock try again later");
        }
        OrderResonseDTO orderResonseDTO = new OrderResonseDTO();
        orderResonseDTO.setOrderNumber(savedOrder.getOrderNumber());
        orderResonseDTO.setId(savedOrder.getId());
        orderResonseDTO.setOrderItemsDto(savedOrder.getOrderItems().stream().map(orderItems -> new OrderItemsDTO(orderItems.getItemName(), orderItems.getPrice(), orderItems.getQuantity())).collect(Collectors.toList()));
        return "order placed successfully";
    }
}
