package com.ducph.mycrm.repository;

import com.ducph.mycrm.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
