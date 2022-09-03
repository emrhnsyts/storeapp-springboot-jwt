package com.emrhnsyts.store.controller;

import com.emrhnsyts.store.request.CategoryCreateRequest;
import com.emrhnsyts.store.response.CategoryResponse;
import com.emrhnsyts.store.response.CategoryWithProductsResponse;
import com.emrhnsyts.store.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{categoryId}")
    public CategoryWithProductsResponse getCategory(@PathVariable("categoryId") Long categoryId) {
        System.out.println("selam");
        return categoryService.getCategory(categoryId);
    }

    @PostMapping
    public CategoryResponse addCategory(@Valid @RequestBody CategoryCreateRequest categoryCreateRequest) {
        return categoryService.addCategory(categoryCreateRequest);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable("categoryId") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Category deleted.");
    }
}
