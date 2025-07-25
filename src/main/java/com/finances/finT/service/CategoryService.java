package com.finances.finT.service;

import java.util.List;
import java.util.Optional;

import com.finances.finT.Controllers.categoryDTO;
import com.finances.finT.models.categories;

public interface CategoryService {
    List<categories> getAllCategories();
    categories saveCategory(categoryDTO categoryDTO);
    Optional<categories> getCategoryById(Long id);
}