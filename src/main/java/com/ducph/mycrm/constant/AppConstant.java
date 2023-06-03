package com.ducph.mycrm.constant;

import org.springframework.data.domain.Page;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public interface AppConstant {

    String CUSTOMER_NOT_FOUND_MSG = "Customer not found!";
    String SYSTEM_ERROR = "The system is under maintenance, please come back later!";
    String EMAIL_FORMAT_ERROR = "Invalid email format!";
    String UNAUTHORIZED_ERROR = "Access denied!";

    static String getCurrentDateTime() {
        return String.valueOf(new Date(new Timestamp(System.currentTimeMillis()).getTime()));
    }

    static Map<Object, Object> convertToPagingFormat(Page<?> data) {
        var result = new LinkedHashMap<>();

        result.put("content", data.getContent());
        result.put("totalPages", data.getTotalPages());
        result.put("totalElements", data.getTotalElements());

        return result;
    }
}
