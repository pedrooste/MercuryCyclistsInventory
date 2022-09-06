package com.mercuryCyclists.Inventory.service;

import com.mercuryCyclists.Inventory.entity.Product;
import com.mercuryCyclists.Inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Add a product to repo
     * @param product
     * @return a product
     */
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
}
