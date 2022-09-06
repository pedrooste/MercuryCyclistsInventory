package com.mercuryCyclists.Inventory.service;

import com.mercuryCyclists.Inventory.entity.Part;
import com.mercuryCyclists.Inventory.entity.Product;
import com.mercuryCyclists.Inventory.repository.PartRepository;
import com.mercuryCyclists.Inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private PartRepository partRepository;

    public ProductService(ProductRepository productRepository, PartRepository partRepository) {
        this.productRepository = productRepository;
        this.partRepository = partRepository;
    }

    /**
     * Add a product to repo
     * @param product
     * @return a product
     */
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Get all products from the repo
     * @return all products
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Get a product according to id
     * @param id
     * @return a product with the given id
     */
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    /**
     * Update the details of a product from repo
     * @param product
     * @param id
     * @return product with updated details
     */
    public Product updateProduct(Product product, Long id) {
        Product p = productRepository.findById(id).orElse(null);
        if (p != null) {
            p.setName(product.getName());
            p.setComment(product.getComment());
            p.setPrice(product.getPrice());
            productRepository.save(p);
        }
        return p;
    }

    /**
     * Delete a product with given id from repo
     * @param id
     */
    public void deleteProduct(Long id) {
        if (productRepository.findById(id).isPresent())
            productRepository.deleteById(id);
    }

    /**
     * Helper method
     * @param id
     * @return product if product exist else null
     */
    private Product productExist(Long id) {
        return productRepository.findById(id).orElse(null);
    }
    /**
     * Add a part to repo
     * @param part
     * @return a part
     */
    public boolean addPart(Long productId, Part part) {
        Product p = productExist(productId);
        if (p == null) return false;
        // add part to product set
        p.getSet().add(part);
        updateProduct(p, productId);
        return true;
    }

    /**
     * Update a part with given ID
     * @param id
     * @param part
     * @return a part with updated details
     */
    public Part updatePart(Long id, Part part) {
        Part p = partRepository.findById(id).orElse(null);
        if (p == null) return null;
        p.setName(part.getName());
        p.setDescription(part.getDescription());
        return partRepository.save(p);
    }

    /**
     * Get all the parts with given product id
     * @param id
     * @return all parts for a product
     */
    public Set<Part> getAllPartsByProductId(Long id) {
        Product product = productExist(id);
        if (product == null) return null;
        return product.getSet();
    }
}
