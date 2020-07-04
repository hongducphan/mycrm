package com.ducph.mycrm.service;

import com.ducph.mycrm.entity.Customer;
import com.ducph.mycrm.repository.CustomerRepository;
import com.ducph.mycrm.util.ApplicationUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final EntityManager em;

    public CustomerServiceImpl(CustomerRepository customerRepository, EntityManager em) {
        this.customerRepository = customerRepository;
        this.em = em;
    }

    @Override
    public Map<String, Object> findAll(Pageable pageable) {
        var customers = customerRepository.findAll(pageable);
        return ApplicationUtils.convertToPagingFormat(customers);
    }

    @Override
    public Optional<Customer> findById(int id) {
        var result = customerRepository.findById(id);
        return result.map(x -> result).orElseThrow(ResourceNotFoundException::new);
    }

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
        return ApplicationUtils.convertToPagingFormat(searchResult);
    }
}
