package com.mercuryCyclists.Inventory.repository;

import com.mercuryCyclists.Inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
