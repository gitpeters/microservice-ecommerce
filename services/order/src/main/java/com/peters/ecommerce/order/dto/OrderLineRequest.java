package com.peters.ecommerce.order.dto;

import lombok.Builder;

@Builder
public record OrderLineRequest(
        Integer orderId,
        Integer productId,
        Integer purchaseQuantity
) {
}
