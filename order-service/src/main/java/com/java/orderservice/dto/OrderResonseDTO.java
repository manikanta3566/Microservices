package com.java.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResonseDTO {
    private Long id;
    private String orderNumber;
    private List<OrderItemsDTO> orderItemsDto;
}
