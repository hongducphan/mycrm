package com.ducph.mycrm.repository;

import com.ducph.mycrm.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("from Customer c " +
           "where c.firstName like :#{#customer.firstName} " +
           "or c.lastName like :#{#customer.lastName} " +
           "or c.email like :#{#customer.email}")
    Page<Customer> searchByCustomer(@Param("customer") Customer customer, Pageable pageable);
}
