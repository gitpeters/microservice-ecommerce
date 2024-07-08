package com.peters.ecommerce.customer.service;

import com.peters.ecommerce.customer.dto.CustomerRequest;
import com.peters.ecommerce.customer.model.Customer;
import com.peters.ecommerce.customer.dto.CustomerResponse;
import com.peters.ecommerce.customer.exceptions.CustomerNotFoundException;
import com.peters.ecommerce.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository repository;
    private final CustomerMapper mapper;
    @Override
    public String createCustomer(CustomerRequest request) {
        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    @Override
    public void updateCustomer(CustomerRequest request) {
        var customer = repository.findById(request.id()).orElseThrow(()-> new CustomerNotFoundException(CUSTOMER_NOT_FOUND(request.id())));
        mergeCustomer(customer, request);
        repository.save(customer);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = repository.findAll();
        if(customers.isEmpty()) {
            return List.of();
        }
        return mapper.toCustomerResponse(customers);
    }

    @Override
    public Boolean existsById(String customerId) {
        return repository.findById(customerId).isPresent();
    }

    @Override
    public CustomerResponse findById(String customerId) {
        return repository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(()-> new CustomerNotFoundException(CUSTOMER_NOT_FOUND(customerId)));
    }

    @Override
    public void deleteCustomer(String customerId) {
        repository.deleteById(customerId);
    }

    private static String CUSTOMER_NOT_FOUND(String customerId) {
        return String.format("Cannot update customer:: No Customer found with the provided ID:: %s", customerId);
    }

    private void  mergeCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstname())){
            customer.setFirstname(request.firstname());
        }
        if(StringUtils.isNotBlank(request.lastname())){
            customer.setLastname(request.lastname());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(StringUtils.isNotBlank(request.phoneNumber())){
            customer.setPhoneNumber(request.phoneNumber());
        }
        if(request.address()!=null){
            customer.setAddress(request.address());
        }
    }
}
