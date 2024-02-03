package com.dev.sunny.controllers;

import com.dev.sunny.exceptions.NoProductFoundException;
import com.dev.sunny.models.Category;
import com.dev.sunny.models.Product;
import com.dev.sunny.repositories.ProductRepository;
import com.dev.sunny.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;
    @MockBean
    private ProductService productService;
    @MockBean
    private ProductRepository productRepository;

    @Test
    void testCreateProduct() {
//        Test the createProduct method
        Product product = new Product();
        product.setTitle("Test Product");
        product.setDescription("Test Description");
        product.setPrice(10.0);
        product.setCategory(new Category());
        product.getCategory().setName("Test Category");

        Mockito.when(this.productService.createProduct(ArgumentMatchers.any(Product.class)))
                .thenReturn(product);

//        Mock the product repository save method
        Mockito.when(this.productRepository.save(ArgumentMatchers.any(Product.class)))
                .thenReturn(product);

        ResponseEntity<Product> responseEntity = this.productController.createProduct(product);
        Assertions.assertEquals(201, responseEntity.getStatusCode().value());
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(product, responseEntity.getBody());

        System.out.println("Test passed.");
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void getProductById() throws NoProductFoundException {
        Mockito.when(this.productRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        long id = 10L;
        Mockito.when(this.productService.getProductById(ArgumentMatchers.anyLong()))
                .thenThrow(new NoProductFoundException("No product found with id [" + id + "]."));

        Assertions.assertThrows(NoProductFoundException.class, () -> this.productController.getProductById(id));

        System.out.println("Test passed.");
    }

    @Test
    void updateProduct() {
    }

    @Test
    void replaceProduct() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void getProductsByCategoryName() {
    }
}