package com.emrhnsyts.store.service;

import com.emrhnsyts.store.entity.Category;
import com.emrhnsyts.store.exception.CategoryNotFoundException;
import com.emrhnsyts.store.repository.CategoryRepository;
import com.emrhnsyts.store.request.CategoryCreateRequest;
import com.emrhnsyts.store.response.CategoryResponse;
import com.emrhnsyts.store.response.CategoryWithProductsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> getCategories() {
        return categoryRepository.findAll().stream().map(category -> {
            return new CategoryResponse(category);
        }).collect(Collectors.toList());
    }

    public CategoryWithProductsResponse getCategory(Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            return new CategoryWithProductsResponse(optionalCategory.get());
        }
        throw new CategoryNotFoundException("Category not found");
    }

    public CategoryResponse addCategory(CategoryCreateRequest categoryCreateRequest) {
        return new CategoryResponse(categoryRepository.save(Category
                .builder()
                .name(categoryCreateRequest.getName())
                .build()));
    }

    public void deleteCategory(Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            categoryRepository.delete(optionalCategory.get());
        } else {
            throw new CategoryNotFoundException("Category not found");
        }
    }

    protected Category getCategoryForService(Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        }
        throw new CategoryNotFoundException("Category not found");
    }
}
