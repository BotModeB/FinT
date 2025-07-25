package com.finances.finT.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.finances.finT.models.categories;
import com.finances.finT.Controllers.categoryDTO;
import com.finances.finT.repositories.CategoryRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<categories> getAllCategories() {
        try {
            // Логирование для отладки
            System.out.println("Fetching all categories from repository");
            return categoryRepository.findAll();
        } catch (Exception e) {
            System.err.println("Error fetching categories: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<categories> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }
    
    @Override
    public categories saveCategory(categoryDTO categoryDto) {
        // Валидация
        if (categoryDto.getName() == null || categoryDto.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }

        // Преобразование DTO -> Entity
        categories category = new categories();
        category.setName(categoryDto.getName());

        return categoryRepository.save(category);
    }
}