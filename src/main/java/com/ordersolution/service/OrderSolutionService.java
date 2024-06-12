package com.ordersolution.service;

import com.ordersolution.model.Customer;
import com.ordersolution.repository.CustomerRepository;
import com.ordersolution.rest.CreateCustomerRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderSolutionService {

    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void createCustomer(CreateCustomerRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request is empty");
        }
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
        customerRepository.save(customer);
    }
}
