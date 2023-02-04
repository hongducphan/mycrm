package com.ducph.mycrm.service;

import com.ducph.mycrm.entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CustomerService {

    Map<Object, Object> findAll(Pageable pageable);

    Optional<Customer> findById(int id);

    List<Customer> criteriaSearch(String value);

    @Retryable(value = RuntimeException.class, maxAttempts = 4, backoff = @Backoff(delay = 2000L))
    Map<Object, Object> searchByCustomer(Customer customer, Pageable pageable);

    @Recover
    Map<Object, Object> getResponseFallback(RuntimeException e);
}
