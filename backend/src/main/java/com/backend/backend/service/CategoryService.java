package com.backend.backend.service;

import com.backend.backend.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.backend.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category saveCategory(Category category) {
        Category nameCategory = categoryRepository.findByName(category.getName());
        if (nameCategory != null) {
            throw new IllegalArgumentException("Le nom de la catégorie existe déjà.");
        }
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        Optional<Category> idCategory = categoryRepository.findById(id);
        Category deletedCategory = idCategory.get();
        categoryRepository.delete(deletedCategory);
    }

    public Category updateCategory(Long id, Category category) {
        Optional<Category> idCategory = categoryRepository.findById(id);
        if (idCategory.isPresent()) {
            Category updatedCategory = idCategory.get();
            Category nameCategory = categoryRepository.findByName(category.getName());
            if (nameCategory != null) {
                throw new IllegalArgumentException("Le nom de la catégorie existe déjà.");
            }
            updatedCategory.setName(category.getName());
            return categoryRepository.save(updatedCategory);
        } else {
            throw new IllegalArgumentException("Aucune catégorie.");
        }
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Aucune catégorie"));
    }
}
