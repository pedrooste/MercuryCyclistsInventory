package com.mercuryCyclists.Inventory.controller;

import com.mercuryCyclists.Inventory.entity.Part;
import com.mercuryCyclists.Inventory.entity.Product;
import com.mercuryCyclists.Inventory.service.ProductService;
import com.mercuryCyclists.Inventory.service.RestfulService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    private ProductService productService;
    private RestfulService restfulService;

    public ProductController(ProductService productService, RestfulService restfulService) {
        this.productService = productService;
        this.restfulService = restfulService;
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
    @GetMapping("{id}")
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
    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id) {
        return new ResponseEntity<>(productService.updateProduct(product, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Delete Successful", HttpStatus.OK);
    }

    /**
     * Helper method, check if a supplier id is valid
     *
     * @param supplierId
     * @return true if id is valid else false
     */
    private boolean validSupplier(Long supplierId) {
        try {
            restfulService.getSupplier(supplierId).getStatusCodeValue();
            return true;
        } catch (HttpServerErrorException e) {
            return false;
        }
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
        if (!validSupplier(part.getSupplierId()))
            return new ResponseEntity<>("Fail to add part", HttpStatus.FAILED_DEPENDENCY);
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
        if (!validSupplier(part.getSupplierId()))
            return new ResponseEntity<>(null, HttpStatus.FAILED_DEPENDENCY);
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
}
