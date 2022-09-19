package com.mercuryCyclists.Inventory.entity;

import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;


@Entity
@Table(name = "Part")
@Data
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private Long supplierId;
    @PositiveOrZero(message = "Quantity must be >= 0")
    @Column(nullable = false)
    private Long quantity;
}
