package com.devsu.api.application.dtos.reports;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class AccountStatementParams {

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaInicial;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaFinal;

    private String numeroCuenta;

    @NotBlank
    private String identificacionCliente;

}
