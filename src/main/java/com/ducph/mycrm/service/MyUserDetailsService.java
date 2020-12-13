package com.ducph.mycrm.service;

import com.ducph.mycrm.entity.Account;
import com.ducph.mycrm.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<Account> account = accountRepository.findById(username);
        return account.map(value -> new User(value.getUsername(), value.getPassword(), new ArrayList<>()))
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found!"));
    }
}
