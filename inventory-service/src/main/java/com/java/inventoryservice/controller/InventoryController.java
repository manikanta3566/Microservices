package com.java.inventoryservice.controller;

import com.java.inventoryservice.dto.InventoryRequest;
import com.java.inventoryservice.dto.InventoryResponse;
import com.java.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v0/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {
    private final InventoryService inventoryService;
    @PostMapping()
    public List<InventoryResponse> checkItemsInStock(@RequestBody InventoryRequest inventoryRequest) throws InterruptedException {
        log.info("wait started");
        Thread.sleep(10000);
        log.info("wait ended");
        return (inventoryService.checkItemsInStock(inventoryRequest));
    }
}
