package com.emrhnsyts.store.response;

import com.emrhnsyts.store.entity.Like;
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
public class ProductResponse {
    private Long id;
    private String name;
    private Double price;
    private Date createdAt;
    private List<CategoryResponse> categories;
    private String imageUrl;
    private List<CommentResponse> comments;
    private List<LikeResponse> likes;


    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.createdAt = product.getCreatedAt();
        this.categories = product.getCategories().stream().map(category -> {
            return new CategoryResponse(category);
        }).collect(Collectors.toList());
        this.comments = product.getComments().stream().map(comment -> {
            return new CommentResponse(comment);
        }).collect(Collectors.toList());
        this.likes = product.getLikes().stream().map(like -> {
            return new LikeResponse(like);
        }).collect(Collectors.toList());
        this.imageUrl = product.getImageUrl();
    }
}
