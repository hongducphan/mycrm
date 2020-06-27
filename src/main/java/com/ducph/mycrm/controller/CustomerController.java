package com.ducph.mycrm.controller;

import com.ducph.mycrm.entity.Customer;
import com.ducph.mycrm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/search/{value}")
    public List<Customer> search(@PathVariable("value") String value) {
//        return customerService.search(value);
        return customerService.criteriaSearch(HtmlUtils.htmlEscape(value));
    }
}
