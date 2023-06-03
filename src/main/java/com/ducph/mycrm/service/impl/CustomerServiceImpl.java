package com.ducph.mycrm.service.impl;

import com.ducph.mycrm.entity.Customer;
import com.ducph.mycrm.repository.CustomerRepository;
import com.ducph.mycrm.service.CustomerService;
import com.ducph.mycrm.constant.AppConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final EntityManager em;
    private final RedisTemplate<Object, Object> redisTemplate;

    @Override
    public Map<Object, Object> findAll(Pageable pageable) {
        var customers = customerRepository.findAll(pageable);
        return AppConstant.convertToPagingFormat(customers);
    }

    @Override
    public Customer findById(int id) {
        redisTemplate.opsForValue().set("duc", "duc value", 5, TimeUnit.SECONDS);
        log.info("Customer Id: {}", id);
        return customerRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
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
        if (redisTemplate.opsForValue().get("duc") == null) {
            log.error("REDIS CACHE NOT FOUND");
            throw new RuntimeException();
        }
        log.info("Redis vaule: {}", redisTemplate.opsForValue().get("duc"));
        var searchResult = customerRepository.findByFirstNameContainsOrLastNameContainsOrEmailContains(
                customer.getFirstName(), customer.getLastName(), customer.getEmail(), pageable);
        return AppConstant.convertToPagingFormat(searchResult);
    }

    @Override
    public Map<Object, Object> getResponseFallback(RuntimeException e) {
        log.info("Fallback call");
        throw new ResourceNotFoundException("Fallback");
    }
}
