package com.ducph.mycrm.util;

import com.ducph.mycrm.dto.ErrorResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleException(ResourceNotFoundException e) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                ApplicationUtils.CUSTOMER_NOT_FOUND_MSG,
                ApplicationUtils.getCurrentDateTime()
        );
        logger.error(ApplicationUtils.CUSTOMER_NOT_FOUND_MSG, e);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleException(BadCredentialsException e) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.FORBIDDEN.value(),
                ApplicationUtils.UNAUTHORIZED_ERROR,
                ApplicationUtils.getCurrentDateTime()
        );
        logger.error("Authorization failed!", e);

        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleException(Exception e) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ApplicationUtils.SYSTEM_ERROR,
                ApplicationUtils.getCurrentDateTime()
        );
        logger.error("System error!", e);

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        Map<String, String> body = new LinkedHashMap<>();
        body.put("timeStamp", String.valueOf(new Date(new Timestamp(System.currentTimeMillis()).getTime())));
        body.put("status", status.getReasonPhrase());

        // Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getField() + ": " + x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", String.valueOf(errors));

        return new ResponseEntity<>(body, headers, status);
    }
}
