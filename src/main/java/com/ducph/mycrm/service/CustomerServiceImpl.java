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
    public List<Customer> findByFirstNameOrLastNameOrEmailLike(String firstName, String lastName, String email) {
        List<Customer> customer = customerRepository.findByFirstNameOrLastNameOrEmailLike(firstName, lastName, email);
        
        if (customer == null) {
            throw new CustomerException("Cannot find customer with first name: <" + firstName
                    + "> or last name: <" + lastName
                    + "> or email: <" + email + ">");
        }
        return customer;
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