package com.ducph.mycrm.dto;

public class JwtResponseDTO {
    
    private final String jwt;

    public JwtResponseDTO(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
