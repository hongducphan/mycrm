package com.ducph.mycrm.controller.customer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerErrorResponse {
    
    private int status;
    private String message;
    private long timeStamp;
}
