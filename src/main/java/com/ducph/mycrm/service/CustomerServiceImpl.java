package com.ducph.mycrm.service;

import com.ducph.mycrm.entity.Customer;
import com.ducph.mycrm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EntityManager em;

    @Override
    public List<Customer> search(String value) {
        return customerRepository.search(value);
    }

    @Override
    public List<Customer> criteriaSearch(String value) {
        String likeValue = "%" + value + "%";
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
}
