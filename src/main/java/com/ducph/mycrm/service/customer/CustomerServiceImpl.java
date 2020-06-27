package com.ducph.mycrm.service.customer;

import com.ducph.mycrm.entity.Customer;
import com.ducph.mycrm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EntityManager em;

    @Override
    public List<Customer> criteriaSearch(String value) {
        var likeValue = "%" + value + "%";
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> root = cq.from(Customer.class);

        Predicate likeFirstName = cb.like(root.get("firstName"), likeValue);
        Predicate likeLastName = cb.like(root.get("lastName"), likeValue);
        Predicate likeEmail = cb.like(root.get("email"), likeValue);

        cq.where(cb.or(likeFirstName, likeLastName, likeEmail));

        TypedQuery<Customer> query = em.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public Map<String, Object> searchByCustomer(String firstName, String lastName, String email, Pageable pageable) {
        var searchResult = customerRepository.searchByCustomer(firstName, lastName, email, pageable);
        var result = new LinkedHashMap<String, Object>();
        
        result.put("content", searchResult.getContent());
        result.put("totalPages", searchResult.getTotalPages());
        result.put("totalElements", searchResult.getTotalElements());
        
        return result;
    }
}