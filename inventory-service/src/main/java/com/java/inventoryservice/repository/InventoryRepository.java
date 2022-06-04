package com.java.inventoryservice.repository;

import com.java.inventoryservice.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    List<Inventory> findByItemNameIn(List<String> itemName);
}
