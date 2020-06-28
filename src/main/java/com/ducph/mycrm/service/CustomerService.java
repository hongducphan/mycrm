package com.ducph.mycrm.service;

import com.ducph.mycrm.entity.Customer;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface CustomerService {

    List<Customer> criteriaSearch(String value);
    
    Map<String, Object> searchByCustomer(String firstName, String lastName, String email, Pageable pageable);
}
