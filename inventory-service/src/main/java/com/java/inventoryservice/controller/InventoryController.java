package com.java.inventoryservice.controller;

import com.java.inventoryservice.dto.InventoryRequest;
import com.java.inventoryservice.dto.InventoryResponse;
import com.java.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v0/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;
    @PostMapping()
    public List<InventoryResponse> checkItemsInStock(@RequestBody InventoryRequest inventoryRequest){
        return (inventoryService.checkItemsInStock(inventoryRequest));
    }
}
