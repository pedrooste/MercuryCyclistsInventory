package com.mercuryCyclists.Inventory.entity;

import lombok.Data;


import javax.persistence.*;


@Entity
@Table(name = "Part")
@Data
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //    @Column(unique = true)
    private String name;
    private String description;
    private Long supplierId;
    private Long quantity;
}
