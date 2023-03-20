package com.devsu.api.application.dtos;

import com.devsu.api.application.validations.Create;
import com.devsu.api.application.validations.PartialUpdate;
import com.devsu.api.application.validations.Update;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
@JsonIgnoreProperties(value = {"contrasena"}, allowSetters = true)
public class ClientDTO {

    @NotBlank(groups = {Create.class, Update.class})
    private String identificacion;
    @NotBlank(groups = {Create.class, Update.class})
    private String nombres;
    @NotBlank(groups = {Create.class, Update.class})
    private String direccion;
    @NotBlank(groups = {Create.class, Update.class})
    private String telefono;
    @NotBlank(groups = {Create.class, Update.class, PartialUpdate.class})
    @Size(min = 2, max = 15, groups = {Create.class, Update.class, PartialUpdate.class})
    private String contrasena;

    @NotNull(groups = {Create.class, Update.class})
    private Boolean estado;

}
