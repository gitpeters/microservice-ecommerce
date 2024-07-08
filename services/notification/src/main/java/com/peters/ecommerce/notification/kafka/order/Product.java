package com.peters.ecommerce.notification.kafka.order;

import java.math.BigDecimal;

public record Product(
        Integer productId,
        String productName,
        String productDescription,
        BigDecimal price,
        Integer quantity
) {
}
