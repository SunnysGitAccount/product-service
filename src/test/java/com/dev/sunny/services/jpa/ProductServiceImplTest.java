package com.dev.sunny.services.jpa;

import com.dev.sunny.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional
    @Rollback
    void testCreateProduct() {
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void getProductById() {
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