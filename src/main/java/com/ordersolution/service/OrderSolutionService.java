package com.ordersolution.service;

import com.ordersolution.model.Customer;
import com.ordersolution.model.CustomerOrder;
import com.ordersolution.repository.CustomerOrderRepository;
import com.ordersolution.repository.CustomerRepository;
import com.ordersolution.rest.dto.CreateCustomerRequest;
import com.ordersolution.rest.dto.CreateOrderRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderSolutionService {

    private final CustomerRepository customerRepository;
    private final CustomerOrderRepository customerOrderRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer createCustomer(CreateCustomerRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request is empty");
        }
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();

        return customerRepository.save(customer);
    }

    public CustomerOrder addOrder(CreateOrderRequest orderRequest) {
        if (orderRequest == null) {
            throw new IllegalArgumentException("Request is empty");
        }
        // check that customer exists
        Optional<Customer> optionalCustomer = customerRepository.findById(orderRequest.getCustomerId());
        if (optionalCustomer.isEmpty()) {
            throw new IllegalArgumentException("No such customer");
        }
        CustomerOrder order = CustomerOrder.builder()
                .customerId(orderRequest.getCustomerId())
                .orderSum(orderRequest.getOrderSum())
                .build();
        return customerOrderRepository.save(order);
    }

    public List<CustomerOrder> getAllCustomerOrders(Long customerId) {
        return customerOrderRepository.findAllByCustomerId(customerId);
    }

    public Long getCustomerDiscount(Long customerId) {
        // check that customer exists
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new IllegalArgumentException("No such customer");
        }
        List<CustomerOrder> allOrders = customerOrderRepository.findAllByCustomerId(customerId);
        if (allOrders.isEmpty()) {
            // no discount
            return 0L;
        }
        BigDecimal sumOfOrders = BigDecimal.ZERO;
        for (CustomerOrder order : allOrders) {
            sumOfOrders = sumOfOrders.add(order.getOrderSum());
        }
        // less than 500 = 0%
        if (sumOfOrders.compareTo(BigDecimal.valueOf(500L)) < 0) {
            return 0L;
        }
        // more than 500 = 5%
        if (sumOfOrders.compareTo(BigDecimal.valueOf(500L)) >= 0
                && sumOfOrders.compareTo(BigDecimal.valueOf(1000L)) < 0) {
            return 5L;
        }
        // more than 1000 = 10%
        return 10L;
    }

}
