package com.ducph.mycrm.controller;

import com.ducph.mycrm.entity.Customer;
import com.ducph.mycrm.service.CustomerService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchByCustomer(@Valid @RequestBody Customer customer, Pageable pageable) {
        var result = customerService.searchByCustomer(customer.getFirstName(), 
                customer.getLastName(), customer.getEmail(), pageable);

        return ResponseEntity.ok(result);
    }
}
