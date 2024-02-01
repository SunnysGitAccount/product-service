package com.dev.sunny.controllers;

import com.dev.sunny.exceptions.NoProductFoundException;
import com.dev.sunny.repositories.ProductRepository;
import com.dev.sunny.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
    void createProduct() {

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