package com.dev.sunny.services.jpa;

import com.dev.sunny.models.Category;
import com.dev.sunny.repositories.CategoryRepository;
import com.dev.sunny.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepo;

    public CategoryServiceImpl(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<Category> getAllCategories() {
        return this.categoryRepo.findAll();
    }
}
