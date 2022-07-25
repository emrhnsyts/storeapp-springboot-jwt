package com.emrhnsyts.store.response;

import com.emrhnsyts.store.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryWithProductsResponse {
    private Long id;
    private String name;
    private List<ProductResponse> products;

    public CategoryWithProductsResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.products = category.getProducts().stream().map(product -> {
            return new ProductResponse(product);
        }).collect(Collectors.toList());
    }
}
