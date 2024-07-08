package com.peters.ecommerce.product.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductPurchaseResponse(
        Integer productId,
        String productName,
        String productDescription,
        BigDecimal price,
        Integer quantity
) {
}
