package com.ducph.mycrm.service;

import com.ducph.mycrm.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    Page<Customer> search(String value, Pageable pageable);

    List<Customer> criteriaSearch(String value);
}
