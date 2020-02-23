package com.ducph.mycrm.service;

import com.ducph.mycrm.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Customer findById(int id);

    void save(Customer customer);

    void deleteById(int id);
}
