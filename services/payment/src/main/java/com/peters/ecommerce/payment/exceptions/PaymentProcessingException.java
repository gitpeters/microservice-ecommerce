package com.peters.ecommerce.payment.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PaymentProcessingException extends RuntimeException {
    private final String message;
}
