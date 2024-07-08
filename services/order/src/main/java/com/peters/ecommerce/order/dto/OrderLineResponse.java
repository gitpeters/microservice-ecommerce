package com.peters.ecommerce.order.dto;

import lombok.Builder;

@Builder
public record OrderLineResponse(
        int orderLineId,
        int quantity
) {
}
