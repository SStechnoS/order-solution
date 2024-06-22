package com.ordersolution.rest;

import com.ordersolution.model.Customer;
import com.ordersolution.model.CustomerOrder;
import com.ordersolution.rest.dto.CreateCustomerRequest;
import com.ordersolution.rest.dto.CreateOrderRequest;
import com.ordersolution.service.OrderSolutionService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class OrderSolutionController {

    private final OrderSolutionService orderSolutionService;


    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomer() {
        List<Customer> allCustomers = orderSolutionService.getAllCustomers();
        return ResponseEntity.ok(allCustomers);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(
            @RequestBody CreateCustomerRequest request) {
        if (request == null
                || StringUtils.isBlank(request.getFirstName())
                || StringUtils.isBlank(request.getLastName())) {
            return ResponseEntity.badRequest().build();
        }
        Customer customer = orderSolutionService.createCustomer(request);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/{customerId}/orders")
    public ResponseEntity<List<CustomerOrder>> getAllCustomerOrders(
            @PathVariable Long customerId
    ) {
        List<CustomerOrder> allCustomerOrders = orderSolutionService.getAllCustomerOrders(customerId);
        return ResponseEntity.ok(allCustomerOrders);
    }

    @PostMapping("/{customerId}/orders")
    public ResponseEntity<CustomerOrder> createCustomerOrder(
            @PathVariable Long customerId,
            @RequestBody CreateOrderRequest request) {
        if (request == null
                || request.getCustomerId() == null
                || request.getOrderSum() == null
                || request.getOrderSum().compareTo(BigDecimal.ZERO) <= 0
                || !customerId.equals(request.getCustomerId())) {
            return ResponseEntity.badRequest().build();
        }
        CustomerOrder order = orderSolutionService.addOrder(request);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{customerId}/discount")
    public ResponseEntity<Long> customerDiscount(
            @PathVariable Long customerId) {
        Long discount = orderSolutionService.getCustomerDiscount(customerId);
        return ResponseEntity.ok(discount);
    }
}
