package com.ducph.mycrm.service;

import com.ducph.mycrm.entity.Customer;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CustomerService {

    Map<String, Object> findAll(Pageable pageable);

    Optional<Customer> findById(int id);

    List<Customer> criteriaSearch(String value);

    Map<String, Object> searchByCustomer(Customer customer, Pageable pageable);
}
