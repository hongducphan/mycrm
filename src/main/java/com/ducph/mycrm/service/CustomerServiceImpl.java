package com.ducph.mycrm.service;

import com.ducph.mycrm.controller.customer.CustomerException;
import com.ducph.mycrm.entity.Customer;
import com.ducph.mycrm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(int id) {
        Optional<Customer> customer = customerRepository.findById(id);

        Customer result;
        if (customer.isPresent()) {
            result = customer.get();
        } else {
            throw new CustomerException("Cannot find customer with ID: " + id);
        }

        return result;
    }

    @Override
    public Customer findByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email);

        if (customer != null) {
            return customer;
        } else {
            throw new CustomerException("Cannot find customer with Email: " + email);
        }
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteById(int id) {
        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isPresent()) {
            customerRepository.deleteById(id);
        } else {
            throw new CustomerException("Cannot find customer with ID: " + id);
        }
    }
}
