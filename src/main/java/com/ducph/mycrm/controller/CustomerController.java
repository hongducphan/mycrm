package com.ducph.mycrm.controller;

import com.ducph.mycrm.entity.Customer;
import com.ducph.mycrm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    
    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable) {
        Map<String, Object> result = customerService.findAll(pageable);
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<Customer> result = customerService.findById(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchByCustomer(@Valid @RequestBody Customer customer, Pageable pageable) {
        Map<String, Object> result = customerService.searchByCustomer(customer, pageable);
        return ResponseEntity.ok(result);
    }
}
