package com.mercuryCyclists.Inventory.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @PositiveOrZero(message = "Price must be >= 0")
    @Column(nullable = false)
    private Double price;
    private String comment;
    @OneToMany(targetEntity = Part.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "partFk", referencedColumnName = "id")
    private Set<Part> set = new HashSet<>();
}
