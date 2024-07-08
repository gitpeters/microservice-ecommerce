package com.peters.ecommerce.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

@Builder
public record PurchaseRequest(
        @NotNull(message = "Product is mandatory")
        Integer productId,
        @PositiveOrZero(message = "Quantity must be positive value")
        Integer quantity
) {
}
