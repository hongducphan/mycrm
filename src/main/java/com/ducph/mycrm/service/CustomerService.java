package com.ducph.mycrm.service;

import com.ducph.mycrm.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> search(String value);
}
