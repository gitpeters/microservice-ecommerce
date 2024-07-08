package com.peters.ecommerce.product.service;

import com.peters.ecommerce.product.dto.CategoryResponse;
import com.peters.ecommerce.product.dto.ProductPurchaseResponse;
import com.peters.ecommerce.product.dto.ProductRequest;
import com.peters.ecommerce.product.dto.ProductResponse;
import com.peters.ecommerce.product.model.Category;
import com.peters.ecommerce.product.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct(ProductRequest request) {
        return Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .availableQuantity(request.availableQuantity())
                .category(Category.builder()
                        .id(request.categoryId())
                        .build())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .availableQuantity(product.getAvailableQuantity())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .categoryDescription(product.getCategory().getDescription())
                .build();
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, Integer quantity) {
        return ProductPurchaseResponse.builder()
                .productId(product.getId())
                .productName(product.getName())
                .price(product.getPrice())
                .productDescription(product.getDescription())
                .quantity(quantity)
                .build();
    }

    public CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}
