package com.peters.ecommerce.payment.exceptions;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
