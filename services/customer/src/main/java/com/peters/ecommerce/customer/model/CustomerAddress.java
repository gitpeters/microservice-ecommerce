package com.peters.ecommerce.customer.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Validated
public class CustomerAddress {
    @NotNull(message = "Street is required")
    private String street;
    @NotNull(message = "House number is required")
    private String houseNumber;
    private String zipCode;
    @NotNull(message = "State is required")
    private String state;
}
