package com.peters.ecommerce.customer.service;

import com.peters.ecommerce.customer.dto.CustomerRequest;
import com.peters.ecommerce.customer.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {
    String createCustomer(CustomerRequest request);

    void updateCustomer(CustomerRequest request);

    List<CustomerResponse> getAllCustomers();

    Boolean existsById(String customerId);

    CustomerResponse findById(String customerId);

    void deleteCustomer(String customerId);
}
