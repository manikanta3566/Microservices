package com.java.inventoryservice.controller;

import com.java.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v0/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;
    @GetMapping("/{item-name}")
    public ResponseEntity<Boolean> isInStock(@PathVariable("item-name") String itemName){
        return new ResponseEntity<>(inventoryService.isInStock(itemName), HttpStatus.OK);
    }
}
