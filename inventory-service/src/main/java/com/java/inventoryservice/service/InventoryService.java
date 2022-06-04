package com.java.inventoryservice.service;

import com.java.inventoryservice.dto.InventoryRequest;
import com.java.inventoryservice.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {
   List<InventoryResponse> checkItemsInStock(InventoryRequest inventoryRequest);
}
