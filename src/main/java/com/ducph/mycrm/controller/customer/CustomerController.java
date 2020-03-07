package com.ducph.mycrm.controller.customer;

import com.ducph.mycrm.entity.Customer;
import com.ducph.mycrm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable int id) {
        return customerService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Customer findByEmail(@RequestParam(value = "email") String email) {
        return customerService.findByEmail(email);
    }

    @PostMapping("/")
    public Customer save(@RequestBody Customer customer) {
        customerService.save(customer);
        return customer;
    }

    @PutMapping("/")
    public Customer update(@RequestBody Customer customer) {
        customerService.save(customer);
        return customer;
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable int id) {
        customerService.deleteById(id);
        return "Deleted customer with ID: " + id;
    }
}
