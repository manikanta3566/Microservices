package com.java.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "tb_order_items")
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String itemName;
    private String price;
    private String quantity;

    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;

    public OrderItems(String itemName, String price, String quantity,Order order) {
        this.itemName=itemName;
        this.price=price;
        this.quantity=quantity;
        this.order=order;
    }
}
