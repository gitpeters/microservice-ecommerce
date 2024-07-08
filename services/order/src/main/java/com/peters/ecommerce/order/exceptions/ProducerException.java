package com.peters.ecommerce.order.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProducerException extends RuntimeException {
    private final String message;
}
