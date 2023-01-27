package com.ducph.mycrm.controller;

import com.ducph.mycrm.entity.Customer;
import com.ducph.mycrm.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable) {
        return ResponseEntity.ok(customerService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return ResponseEntity.ok(customerService.findById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchByCustomer(@Valid @RequestBody Customer customer, Pageable pageable) {
        return ResponseEntity.ok(customerService.searchByCustomer(customer, pageable));
    }
}
