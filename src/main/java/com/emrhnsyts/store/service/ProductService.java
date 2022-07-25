package com.emrhnsyts.store.service;

import com.emrhnsyts.store.entity.Product;
import com.emrhnsyts.store.exception.ProductNotFoundException;
import com.emrhnsyts.store.repository.ProductRepository;
import com.emrhnsyts.store.request.ProductCreateRequest;
import com.emrhnsyts.store.response.ProductCreateResponse;
import com.emrhnsyts.store.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public List<ProductResponse> getProducts() {
        return productRepository.findAll().stream().map(product -> {
            return new ProductResponse(product);
        }).collect(Collectors.toList());
    }

    public ProductResponse getProduct(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            return new ProductResponse(optionalProduct.get());
        }
        throw new ProductNotFoundException("Product not found");
    }


    public void deleteProduct(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            productRepository.delete(optionalProduct.get());
        } else {
            throw new ProductNotFoundException("Product not found");
        }
    }

    public ProductCreateResponse addProduct(ProductCreateRequest productCreateRequest) {
        Product product = new Product();
        product.setImageUrl(productCreateRequest.getImageUrl());
        product.setName(productCreateRequest.getName());
        product.setPrice(productCreateRequest.getPrice());
        product.setQuantity(productCreateRequest.getQuantity());
        product.setCategories(productCreateRequest.getCategories()
                .stream()
                .map(cId -> {
                    return categoryService.getCategoryForService(cId);
                }).collect(Collectors.toList()));

        return new ProductCreateResponse(productRepository.save(product));
    }


    protected Product getProductForService(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        }
        throw new ProductNotFoundException("Product not found");
    }


}
