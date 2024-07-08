package com.peters.ecommerce.order.dto;

import com.peters.ecommerce.order.model.PaymentMethod;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderResponse(
        Integer orderId,
        String reference,
        BigDecimal totalPrice,
        PaymentMethod paymentMethod,
        String customerId
) {
}
