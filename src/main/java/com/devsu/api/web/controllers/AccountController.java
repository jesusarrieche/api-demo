package com.devsu.api.web.controllers;

import com.devsu.api.application.dtos.AccountDTO;
import com.devsu.api.application.validations.Create;
import com.devsu.api.application.validations.Update;
import com.devsu.api.domain.services.AccountService;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/cuentas")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable @NotNull String accountNumber) {
        log.info("GET - /cuentas/{}", accountNumber);

        return new ResponseEntity<>(this.accountService.findAcocountByAcocuntNumber(accountNumber), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountDTO> saveAccount(@Validated(Create.class) @RequestBody AccountDTO accountDTO) {
        log.info("POST - /cuentas");

        return new ResponseEntity<>(this.accountService.createAccount(accountDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{accountNumber}")
    public ResponseEntity<AccountDTO> updateAccount(
            @PathVariable @NotNull String accountNumber,
            @Validated(Update.class) @RequestBody AccountDTO accountDTO
    ) {
        log.info("PUT - /cuentas/{}", accountNumber);

        return new ResponseEntity<>(this.accountService.updateAccount(accountNumber, accountDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<?> deleteAccount(@PathVariable @NotNull String accountNumber) {
        log.info("DELETE - /cuentas/{}", accountNumber);
        this.accountService.deleteAccountByAccountNumber(accountNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
