package com.ducph.mycrm.controller;

import com.ducph.mycrm.controller.exception.CustomerErrorResponse;
import com.ducph.mycrm.service.CustomerService;
import com.ducph.mycrm.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/search/{value}")
    public ResponseEntity<?> search(@PathVariable("value") String value, Pageable pageable) {
//        var result = customerService.criteriaSearch(HtmlUtils.htmlEscape(value));
        var result = customerService.search(value, pageable);

        return result.isEmpty() ? ResponseEntity.ok(
            new CustomerErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                MessageUtils.CUSTOMER_NOT_FOUND_MSG,
                String.valueOf(new Date(new Timestamp(System.currentTimeMillis()).getTime()))
            )
        ) : ResponseEntity.ok(result);
    }
}
