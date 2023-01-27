package com.ducph.mycrm.repository;

import com.ducph.mycrm.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Page<Customer> findByFirstNameContainsOrLastNameContainsOrEmailContains(String firstName, String lastName, String email, Pageable pageable);
}
