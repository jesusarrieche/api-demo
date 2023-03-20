package com.devsu.api.application.dtos.reports;

import com.devsu.api.domain.enums.AccountType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountSummary {
    private String numeroCuenta;

    private AccountType tipo;

    private BigDecimal saldo;

    private Long debitos;

    private Long creditos;

    private BigDecimal totalDebito;

    private BigDecimal totalCredito;

}
