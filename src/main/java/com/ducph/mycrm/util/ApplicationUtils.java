package com.ducph.mycrm.util;

import java.sql.Timestamp;
import java.util.Date;

public interface ApplicationUtils {

    String CUSTOMER_NOT_FOUND_MSG = "Customer not found!";
    String SYSTEM_ERROR = "The system is under maintenance, please come back later!";
    String EMAIL_FORMAT_ERROR = "Invalid email format!";

    static String getCurrentDateTime() {
        return String.valueOf(new Date(new Timestamp(System.currentTimeMillis()).getTime()));
    }
}
