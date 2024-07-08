package com.peters.ecommerce.customer.dto;

import com.peters.ecommerce.customer.model.CustomerAddress;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull(message = "firstname is required")
         String firstname,
        @NotNull(message = "lastname is required")
         String lastname,
        @NotNull(message = "email is required")
        @Email(message = "Invalid email address")
         String email,
         @NotNull(message = "phone number is required")
         String phoneNumber,
         CustomerAddress address
) {
}
