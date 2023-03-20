package com.devsu.api.application.dtos;

import com.devsu.api.application.validations.Create;
import com.devsu.api.application.validations.Update;
import com.devsu.api.domain.enums.MovementType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MovementDTO {

    @NotBlank(groups = Update.class)
    private Long movimientoId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate fecha = LocalDate.now();

    private MovementType tipo;

    @NotNull(groups = {Create.class, Update.class})
    @DecimalMin(value = "0.01")
    private BigDecimal monto;

    @NotBlank(groups = {Create.class})
    private String numeroCuenta;
    private BigDecimal saldo;

}
