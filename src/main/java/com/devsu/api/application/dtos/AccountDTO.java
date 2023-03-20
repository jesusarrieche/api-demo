package com.devsu.api.application.dtos;

import com.devsu.api.application.validations.Create;
import com.devsu.api.application.validations.Update;
import com.devsu.api.domain.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDTO {

    @NotBlank(groups = {Create.class})
    private String identificacionCliente;
    @NotBlank(groups = {Create.class})
    @JsonProperty("Numero Cuenta")
    private String numeroCuenta;

    @NotNull(groups = {Create.class})
    @Enumerated(EnumType.STRING)
    private AccountType tipo;

    @NotNull(groups = {Create.class})
    @DecimalMin(value = "0.01")
    private BigDecimal saldo;

    @NotNull(groups = {Create.class})
    private Boolean estado;

}
