package com.peters.ecommerce.product.dto;

import lombok.Builder;

import java.math.BigDecimal;
@Builder
public record ProductResponse(
        Integer id,
        String name,
        String description,
        Integer availableQuantity,
        BigDecimal price,
        Integer categoryId,
        String categoryName,
        String categoryDescription
) {
}
