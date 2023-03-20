package com.devsu.api.domain.repositories;

import com.devsu.api.domain.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(String accountNumber);

    Optional<Account> findByAccountNumberAndIdNot(String accountNumber, Long id);

}
