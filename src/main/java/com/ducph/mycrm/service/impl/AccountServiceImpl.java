package com.ducph.mycrm.service.impl;

import com.ducph.mycrm.entity.Account;
import com.ducph.mycrm.repository.AccountRepository;
import com.ducph.mycrm.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public Account save(Account account) {
        var encodedPassword = passwordEncoder.encode(account.getPassword());
        account.setPassword(encodedPassword);
        return accountRepository.save(account);
    }
}
