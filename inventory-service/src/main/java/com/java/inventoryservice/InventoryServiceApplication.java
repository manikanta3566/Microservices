package com.java.inventoryservice;

import com.java.inventoryservice.entity.Inventory;
import com.java.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class InventoryServiceApplication {

    @Autowired
    private InventoryRepository inventoryRepository;

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @PostConstruct
    @Transactional
    void addStocksToDb() {
        Inventory i1 = new Inventory();
        i1.setItemName("iphone 12");
        i1.setQuantity(2L);
        inventoryRepository.save(i1);
        Inventory i2 = new Inventory();
        i2.setItemName("iphone 13");
        i2.setQuantity(0L);
        inventoryRepository.save(i2);
    }
}
