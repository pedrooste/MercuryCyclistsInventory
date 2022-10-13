package com.mercuryCyclists.Inventory.controller;

import com.mercuryCyclists.Inventory.entity.Part;
import com.mercuryCyclists.Inventory.entity.Product;
import com.mercuryCyclists.Inventory.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Add product restful apo
     *
     * @param product
     * @return Response
     */
    @PostMapping()
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
    }

    /**
     * Get all product restful api
     *
     * @return all products
     */
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Get product according to id restful api
     *
     * @param id
     * @return a response about product with given id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    /**
     * Update a product details restful api
     *
     * @param product
     * @param id
     * @return a response about product with updated details
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id) {
        return new ResponseEntity<>(productService.updateProduct(product, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Delete Successful", HttpStatus.OK);
    }

    /**
     * Add part with given product id restful api
     *
     * @param productId
     * @param part
     * @return true if succeeded else false
     */
    @PostMapping("/{id}/part")
    public ResponseEntity<String> addPart(@PathVariable("id") Long productId, @RequestBody Part part) {
        if (productService.addPart(productId, part))
            return new ResponseEntity<>("Part added", HttpStatus.CREATED);
        return new ResponseEntity<>("Fail to add part", HttpStatus.FAILED_DEPENDENCY);
    }

    /**
     * Update a part with given part ID restful api
     *
     * @param productId
     * @param partId
     * @param part
     * @return a part with updated details
     */
    @PutMapping("/{productId}/part/{partId}")
    public ResponseEntity<Part> updatePart(@PathVariable("productId") Long productId, @PathVariable("partId") Long partId, @RequestBody Part part) {
        return new ResponseEntity<>(productService.updatePart(productId, partId, part), HttpStatus.OK);
    }

    /**
     * Get all parts with given product id restful api
     *
     * @param id
     * @return all parts of a product
     */
    @GetMapping("{id}/part")
    public Set<Part> getAllPartsByProductId(@PathVariable Long id) {
        return productService.getAllPartsByProductId(id);
    }

    @DeleteMapping("{productId}/part/{partId}")
    public ResponseEntity<String> deletePart(@PathVariable("productId") Long productId, @PathVariable("partId") Long partId) {
        productService.deletePart(productId, partId);
        return new ResponseEntity<>("Delete Successful", HttpStatus.OK);
    }

    // Get product with quantity
    @GetMapping("{productId}/quantity/{quantity}")
    public Product getProductWithQuantity(@PathVariable("productId") Long productId, @PathVariable("quantity") Long quantity) {
        if (productService.checkProductQuantity(productId, quantity)) return productService.getProduct(productId);
        return null;
    }

    // get all parts of a product with quantity
    @GetMapping("{productId}/part/quantity/{quantity}")
    public Set<Part> getAllPartsByProductIdWithQuantity(@PathVariable("productId") Long productId, @PathVariable("quantity") Long quantity) {
        if (productService.checkPartQuantity(productId, quantity))
            return productService.getAllPartsByProductId(productId);
        return null;
    }
}
