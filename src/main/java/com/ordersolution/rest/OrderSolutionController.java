package com.ordersolution.rest;

import com.ordersolution.model.Customer;
import com.ordersolution.service.OrderSolutionService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<CreateCustomerRequest> createCustomer(
            @RequestBody CreateCustomerRequest request) {
        if (request == null
                || StringUtils.isBlank(request.getFirstName())
                || StringUtils.isBlank(request.getLastName())) {
            return ResponseEntity.badRequest().build();
        }
        orderSolutionService.createCustomer(request);
        return ResponseEntity.ok(request);
    }
}
