package com.devsu.api.application.dtos;

import com.devsu.api.application.validations.Create;
import com.devsu.api.application.validations.Update;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
@JsonIgnoreProperties(value = {"contrasena"}, allowSetters = true)
public class ClientDTO {

    @NotBlank(groups = {Create.class})
    private String identificacion;
    @NotBlank(groups = {Create.class})
    private String nombres;
    @NotBlank(groups = {Create.class})
    private String direccion;
    @NotBlank(groups = {Create.class})
    private String telefono;
    @NotBlank(groups = {Create.class})
    private String contrasena;

    @NotNull(groups = {Create.class})
    private Boolean estado;

}
