package com.mercuryCyclists.Inventory.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private String comment;
}
