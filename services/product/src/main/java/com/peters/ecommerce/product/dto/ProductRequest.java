package com.peters.ecommerce.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,
        @NotNull(message = "Product name is required")
        String name,
        @NotNull(message = "Product description is required")
        String description,
        @PositiveOrZero(message = "Available quantity should be positive value")
        Integer availableQuantity,
        @PositiveOrZero(message = "Product price should be positive value")
        BigDecimal price,
        @NotNull(message = "Product category is required")
        Integer categoryId
) {
}
