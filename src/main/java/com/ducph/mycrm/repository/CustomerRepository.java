package com.ducph.mycrm.repository;

import com.ducph.mycrm.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("from Customer c where c.firstName like %:value% or c.lastName like %:value% or c.email like %:value%")
    Page<Customer> search(@Param("value") String value, Pageable pageable);
    
    @Query("from Customer c where c.firstName like %:firstName% or c.lastName like %:lastName% or c.email like %:email%")
    Page<Customer> searchByCustomer(@Param("firstName") String firstName,
                                    @Param("lastName") String lastName,
                                    @Param("email") String email,
                                    Pageable pageable);
}
