package com.java.inventoryservice.service.impl;

import com.java.inventoryservice.dto.InventoryRequest;
import com.java.inventoryservice.dto.InventoryResponse;
import com.java.inventoryservice.entity.Inventory;
import com.java.inventoryservice.repository.InventoryRepository;
import com.java.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    @Override
    public List<InventoryResponse> checkItemsInStock(InventoryRequest inventoryRequest) {
        List<Inventory> inventoryList = inventoryRepository.findByItemNameIn(inventoryRequest.getItemsList());
        List<InventoryResponse> itemsInStock= inventoryList.stream().map(inventory ->
                InventoryResponse.builder()
                        .itemName(inventory.getItemName())
                        .isInStock(inventory.getQuantity() > 0)
                        .build()).collect(Collectors.toList());
        return itemsInStock;

    }
}
