package com.ducph.mycrm.service;

import com.ducph.mycrm.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        var account = accountRepository.findById(username);
        return account.map(value -> new User(value.getUsername(), value.getPassword(), new ArrayList<>()))
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found!"));
    }
}
