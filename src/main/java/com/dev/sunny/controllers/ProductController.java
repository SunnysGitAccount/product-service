package com.dev.sunny.controllers;

import com.dev.sunny.exceptions.NoProductFoundException;
import com.dev.sunny.models.Product;
import com.dev.sunny.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = this.productService.createProduct(product);
        if (savedProduct == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(this.productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long id) throws NoProductFoundException {
        Product foundProduct = this.productService.getProductById(id);
        return new ResponseEntity<>(foundProduct, HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return new ResponseEntity<>(this.productService.updateProduct(id, product), HttpStatus.OK);
    }

    @PutMapping("/replace/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return new ResponseEntity<>(this.productService.replaceProduct(id, product), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        if (!this.productService.deleteProduct(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Product>> getProductsByCategoryName(@PathVariable("categoryName") String categoryName) {
        return new ResponseEntity<>(this.productService.getProductsByCategoryName(categoryName), HttpStatus.OK);
    }
}
