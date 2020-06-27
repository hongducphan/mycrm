package com.ducph.mycrm.controller.exception;

import com.ducph.mycrm.util.MessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.util.Date;

@ControllerAdvice
public class CustomerRestExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException(ResourceNotFoundException e) {
        CustomerErrorResponse error = new CustomerErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                MessageUtils.CUSTOMER_NOT_FOUND_MSG,
                String.valueOf(new Date(new Timestamp(System.currentTimeMillis()).getTime())));
        logger.error("System error: " + e.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException(Exception e) {
        CustomerErrorResponse error = new CustomerErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                MessageUtils.SYSTEM_ERROR,
                String.valueOf(new Date(new Timestamp(System.currentTimeMillis()).getTime())));
        logger.error("System error: " + e.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
