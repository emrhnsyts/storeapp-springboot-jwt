package com.emrhnsyts.store.response;

import com.emrhnsyts.store.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateResponse {
    private Long id;
    private String name;
    private Double price;
    private Date createdAt;
    private List<CategoryResponse> categories;
    private String imageUrl;

    public ProductCreateResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.createdAt = product.getCreatedAt();
        this.imageUrl = product.getImageUrl();
        this.categories = product.getCategories().stream().map((category -> {
            return new CategoryResponse(category);
        })).collect(Collectors.toList());
    }
}
