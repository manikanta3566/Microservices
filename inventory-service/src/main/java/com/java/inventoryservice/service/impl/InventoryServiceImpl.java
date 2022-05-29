package com.java.inventoryservice.service.impl;

import com.java.inventoryservice.repository.InventoryRepository;
import com.java.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    @Override
    public boolean isInStock(String itemName) {
        return inventoryRepository.findByItemName(itemName).isPresent();
    }
}
