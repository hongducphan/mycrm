package com.ducph.mycrm.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponseDTO {
    
    private int status;
    private String message;
    private String dateTime;
}
