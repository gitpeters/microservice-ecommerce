package com.peters.ecommerce.order.dto;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        String phoneNumber
) {
}
