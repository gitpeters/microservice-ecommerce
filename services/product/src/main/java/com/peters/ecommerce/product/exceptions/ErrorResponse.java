package com.peters.ecommerce.product.exceptions;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
