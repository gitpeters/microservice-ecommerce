package com.peters.ecommerce.order.dto;

import java.math.BigDecimal;

public record PurchaseResponse(
        Integer productId,
        String productName,
        String productDescription,
        BigDecimal price,
        Integer quantity
) {
}
