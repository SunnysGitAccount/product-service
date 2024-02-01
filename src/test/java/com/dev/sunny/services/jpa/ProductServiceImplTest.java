package com.dev.sunny.services.jpa;

import com.dev.sunny.models.Category;
import com.dev.sunny.models.Product;
import com.dev.sunny.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productService;
    @MockBean
    private ProductRepository productRepository;
    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setTitle("Test Product");
        product.setDescription("Test Description");
        product.setPrice(10.0);
        product.setCategory(new Category());
        product.getCategory().setName("Test Category");
    }

    @Test
    @Transactional
    void testCreateProduct() {
        Mockito.when(this.productRepository.save(product))
                .thenReturn(product);

        Product savedProduct = this.productService.createProduct(product);
        assertNotNull(savedProduct);
    }

    @Test
    void getAllProducts() {
        Mockito.when(this.productRepository.findAll())
                .thenReturn(List.of(product));

        List<Product> allProducts = this.productService.getAllProducts();
        Assertions.assertEquals(1, allProducts.size());
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