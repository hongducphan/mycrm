package com.ducph.mycrm.repository;

import com.ducph.mycrm.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByFirstNameOrLastNameOrEmailLike(String firstName, String lastName, String email);
}
