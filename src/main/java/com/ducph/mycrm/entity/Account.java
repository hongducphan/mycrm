package com.ducph.mycrm.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "account")
@Data
public class Account {
    
    @Id
    @Column(name = "username")
    @NotBlank
    private String username;
    
    @Column(name = "password")
    @NotBlank
    private String password;
}
