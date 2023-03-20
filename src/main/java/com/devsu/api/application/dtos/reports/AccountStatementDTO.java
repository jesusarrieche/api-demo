package com.devsu.api.application.dtos.reports;

import lombok.Data;

import java.util.List;

@Data
public class AccountStatementDTO {

    private String cliente;

    private List<AccountSummary> cuentas;

}
