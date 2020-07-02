package com.ducph.mycrm.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
@Data
public class Account {
    
    @Id
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;
}
