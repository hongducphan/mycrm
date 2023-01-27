package com.ducph.mycrm.controller;

import com.ducph.mycrm.entity.Account;
import com.ducph.mycrm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid Account account) {
        Account savedAccount = accountService.save(account);
        return ResponseEntity.ok(savedAccount);
    }
}
