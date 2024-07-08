package com.peters.ecommerce.customer.dto;

import com.peters.ecommerce.customer.model.CustomerAddress;
import lombok.*;


@Builder
public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        String phoneNumber,
        CustomerAddress address
) {
}
