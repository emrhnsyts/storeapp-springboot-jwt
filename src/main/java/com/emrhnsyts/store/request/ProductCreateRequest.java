package com.emrhnsyts.store.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequest {
    @NotBlank(message = "Product name can not be blank.")
    @Length(min = 5, max = 20, message = "Name length must be between 5 and 20.")
    private String name;
    private Integer quantity;
    private Double price;
    private List<Long> categories;
    private String imageUrl;
}
