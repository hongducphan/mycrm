package com.ducph.mycrm.exception;

import com.ducph.mycrm.constant.AppConstant;
import com.ducph.mycrm.dto.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleException(ResourceNotFoundException e) {
        var error = new ErrorResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                AppConstant.CUSTOMER_NOT_FOUND_MSG,
                AppConstant.getCurrentDateTime()
        );
        log.error(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleException(BadCredentialsException e) {
        var error = new ErrorResponseDTO(
                HttpStatus.FORBIDDEN.value(),
                AppConstant.UNAUTHORIZED_ERROR,
                AppConstant.getCurrentDateTime()
        );
        log.error("Authorization failed: " + e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleException(Exception e) {
        var error = new ErrorResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                AppConstant.SYSTEM_ERROR,
                AppConstant.getCurrentDateTime()
        );
        log.error("System error: " + e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        var body = new LinkedHashMap<>();
        body.put("timeStamp", String.valueOf(new Date(new Timestamp(System.currentTimeMillis()).getTime())));
        body.put("status", status.getReasonPhrase());

        // Get all errors
        var errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getField() + ": " + x.getDefaultMessage())
                .toList();

        body.put("errors", String.valueOf(errors));
        return new ResponseEntity<>(body, headers, status);
    }
}
