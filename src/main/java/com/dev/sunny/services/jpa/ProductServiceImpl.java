package com.dev.sunny.services.jpa;

import com.dev.sunny.exceptions.NoProductFoundException;
import com.dev.sunny.models.Category;
import com.dev.sunny.models.Product;
import com.dev.sunny.repositories.CategoryRepository;
import com.dev.sunny.repositories.ProductRepository;
import com.dev.sunny.services.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;

    public ProductServiceImpl(ProductRepository productRepo, CategoryRepository categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Product createProduct(Product product) {
        Optional<Category> category = categoryRepo.findByName(product.getCategory().getName());
        category.ifPresent(product::setCategory);
        product.setIsDeleted(false);
        product.getCategory().setIsDeleted(false);
        return this.productRepo.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepo.findAll();
    }

    @Override
    public Product getProductById(Long id) throws NoProductFoundException {
        return this.productRepo.findById(id).orElseThrow(() -> new NoProductFoundException("No product found with id: " + id));
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> savedProduct = this.productRepo.findById(id);
        if (savedProduct.isPresent()) {
            Product updatedProduct = savedProduct.get();
            String productTitle = product.getTitle();
            String productDescription = product.getDescription();
            Double productPrice = product.getPrice();
            Category productCategory = product.getCategory();
            boolean isProductDeleted = product.getIsDeleted() != null ? product.getIsDeleted() : false;

            if (StringUtils.hasText(productTitle)) updatedProduct.setTitle(productTitle);
            if (StringUtils.hasText(productDescription)) updatedProduct.setDescription(productDescription);
            if (productPrice != null) updatedProduct.setPrice(productPrice);
            if (productCategory != null) throw new RuntimeException("Category cannot be updated");

            if (isProductDeleted) updatedProduct.setIsDeleted(true);
            return this.productRepo.save(updatedProduct);
        }
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        Optional<Product> savedProduct = this.productRepo.findById(id);
        if (savedProduct.isPresent()) {
            Product updatedProduct = savedProduct.get();
            updatedProduct.setTitle(product.getTitle());
            updatedProduct.setDescription(product.getDescription());
            updatedProduct.setPrice(product.getPrice());
            if (product.getCategory() != null && product.getCategory().getName() != null) throw new RuntimeException("Category cannot be updated");
            updatedProduct.setIsDeleted(product.getIsDeleted());

            return this.productRepo.save(updatedProduct);
        }
        return null;
    }

    @Override
    public Boolean deleteProduct(Long id) {
        if (this.productRepo.existsById(id)) {
            this.productRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Product> getProductsByCategoryName(String categoryName) {
        return this.productRepo.findProductsByCategoryName(categoryName);
    }
}
