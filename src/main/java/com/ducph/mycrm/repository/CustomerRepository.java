package com.ducph.mycrm.repository;

import com.ducph.mycrm.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "customers", itemResourceRel = "customer")
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @RestResource(path = "/withValue")
    @Query("from Customer where firstName like %:value% or lastName like %:value% or email like %:value%")
    List<Customer> search(@Param("value") String value);
}
