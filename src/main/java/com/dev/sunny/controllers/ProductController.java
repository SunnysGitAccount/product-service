package com.dev.sunny.controllers;

import com.dev.sunny.exceptions.NoProductFoundException;
import com.dev.sunny.models.Product;
import com.dev.sunny.services.ProductService;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private final ObservationRegistry registry;

    public ProductController(ProductService productService, ObservationRegistry registry) {
        this.productService = productService;
        this.registry = registry;
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = Observation.createNotStarted("createProduct", this.registry)
                .observe(() -> this.productService.createProduct(product));
        if (savedProduct == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> allProducts = Observation.createNotStarted("getAllProducts", this.registry)
                .observe(this.productService::getAllProducts);
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long id) throws NoProductFoundException {
        Product foundProduct = this.productService.getProductById(id);
        return new ResponseEntity<>(Observation.createNotStarted("getProductByID", this.registry)
                .observe(() -> foundProduct), HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        Product updatedProduct = Observation.createNotStarted("updateProductByID", this.registry)
                .observe(() -> this.productService.updateProduct(id, product));
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @PutMapping("/replace/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        Product replacedProduct = Observation.createNotStarted("replaceProductById", this.registry)
                .observe(() -> this.productService.replaceProduct(id, product));
        return new ResponseEntity<>(replacedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        Boolean isProductDeleted = Observation.createNotStarted("deleteProductByID", this.registry)
                .observe(() -> this.productService.deleteProduct(id));
        if (Boolean.FALSE.equals(isProductDeleted)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Product>> getProductsByCategoryName(@PathVariable("categoryName") String categoryName) {
        return new ResponseEntity<>(this.productService.getProductsByCategoryName(categoryName), HttpStatus.OK);
    }
}
