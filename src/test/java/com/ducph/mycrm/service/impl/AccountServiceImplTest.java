package com.ducph.mycrm.service.impl;

import com.ducph.mycrm.entity.Account;
import com.ducph.mycrm.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AccountRepository accountRepository;

    @Test
    void save() {
        // given
        var account = new Account();
        account.setUsername("admin");
        account.setPassword("password");

        // when
        when(passwordEncoder.encode(anyString())).thenReturn("123456");
        when(accountRepository.save(any())).thenReturn(new Account());

        // then
        var result = accountService.save(account);
        assertNotNull(result);
    }
}