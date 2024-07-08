package com.peters.ecommerce.customer.service;

import com.peters.ecommerce.customer.dto.CustomerRequest;
import com.peters.ecommerce.customer.model.Customer;
import com.peters.ecommerce.customer.dto.CustomerResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request) {
        return Customer.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .address(request.address())
                .build();
    }

    public List<CustomerResponse> toCustomerResponse(List<Customer> customers) {
        return customers.stream().map(this::fromCustomer).toList();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstname(customer.getFirstname())
                .lastname(customer.getLastname())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .address(customer.getAddress())
                .build();
    }
}
