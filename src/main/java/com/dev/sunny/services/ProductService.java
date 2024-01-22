package com.dev.sunny.services;

import com.dev.sunny.exceptions.NoProductFoundException;
import com.dev.sunny.models.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long id) throws NoProductFoundException;
    Product updateProduct(Long id, Product product);
    Product replaceProduct(Long id, Product product);
    Boolean deleteProduct(Long id);
    List<Product> getProductsByCategoryName(String categoryName);
}
