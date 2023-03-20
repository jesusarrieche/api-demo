package com.devsu.api.domain.services;


import com.devsu.api.application.dtos.AccountDTO;

public interface AccountService {
    public AccountDTO findAcocountByAcocuntNumber(String accountNumber);
    public AccountDTO createAccount(AccountDTO accountDTO);
    public AccountDTO updateAccount(String accountNumber, AccountDTO accountDTO);
    public void deleteAccountByAccountNumber(String accountNumber);
}
