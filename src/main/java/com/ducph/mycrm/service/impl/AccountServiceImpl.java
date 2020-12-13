package com.ducph.mycrm.service.impl;

import com.ducph.mycrm.entity.Account;
import com.ducph.mycrm.repository.AccountRepository;
import com.ducph.mycrm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account save(Account account) {
        String encodedPassword = passwordEncoder.encode(account.getPassword());
        account.setPassword(encodedPassword);
        return accountRepository.save(account);
    }
}
