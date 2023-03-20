package com.devsu.api.web.controllers;

import com.devsu.api.application.dtos.AccountDTO;
import com.devsu.api.application.dtos.MovementDTO;
import com.devsu.api.application.validations.Create;
import com.devsu.api.domain.services.AccountService;
import com.devsu.api.domain.services.MovementService;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/movimientos")
public class MovementController {

    private MovementService movementService;

    public MovementController(MovementService movementService) {
        this.movementService = movementService;
    }
//    @GetMapping
//    public ResponseEntity<AccountDTO> getAccount(@PathVariable @NotNull String accountNumber) {
//        log.info("GET - /cuentas/{}", accountNumber);
//
//        return new ResponseEntity<>(this.accountService.findAcocountByAcocuntNumber(accountNumber), HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<MovementDTO> saveMovement(@Validated(Create.class) @RequestBody MovementDTO movementDTO) {
        log.info("POST - /movimientos");

        return new ResponseEntity<>(this.movementService.createMovement(movementDTO), HttpStatus.CREATED);
    }

//    @PutMapping("/{accountNumber}")
//    public ResponseEntity<AccountDTO> updateAccount(
//            @PathVariable @NotNull String accountNumber,
//            @Validated @RequestBody AccountDTO accountDTO
//    ) {
//        log.info("PUT - /cuentas/{}", accountNumber);
//
//        return new ResponseEntity<>(this.accountService.updateAccount(accountNumber, accountDTO), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{accountNumber}")
//    public ResponseEntity<?> deleteAccount(@PathVariable @NotNull String accountNumber) {
//        log.info("DELETE - /cuentas/{}", accountNumber);
//        this.accountService.deleteAccountByAccountNumber(accountNumber);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
