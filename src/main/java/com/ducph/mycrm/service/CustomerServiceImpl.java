package com.ducph.mycrm.service;

import com.ducph.mycrm.entity.Customer;
import com.ducph.mycrm.repository.CustomerRepository;
import com.ducph.mycrm.util.ApplicationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final EntityManager em;

    @Override
    public Map<Object, Object> findAll(Pageable pageable) {
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
        var cb = em.getCriteriaBuilder();

        var cq = cb.createQuery(Customer.class);
        var root = cq.from(Customer.class);

        var likeFirstName = cb.like(root.get("firstName"), likeValue);
        var likeLastName = cb.like(root.get("lastName"), likeValue);
        var likeEmail = cb.like(root.get("email"), likeValue);

        cq.where(cb.or(likeFirstName, likeLastName, likeEmail));

        var query = em.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public Map<Object, Object> searchByCustomer(Customer customer, Pageable pageable) {
        var searchResult = customerRepository.searchByCustomer(customer, pageable);
        return ApplicationUtils.convertToPagingFormat(searchResult);
    }
}
