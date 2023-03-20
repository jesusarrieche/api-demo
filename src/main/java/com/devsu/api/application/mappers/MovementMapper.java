package com.devsu.api.application.mappers;

import com.devsu.api.application.dtos.MovementDTO;
import com.devsu.api.domain.entities.Movement;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovementMapper {

    @Mapping(source = "id",                         target = "movimientoId")
    @Mapping(source = "date",                       target = "fecha")
    @Mapping(source = "movementType",               target = "tipo" )
    @Mapping(source = "amount",                     target = "monto" )
    @Mapping(source = "account.accountNumber",      target = "numeroCuenta" )
    @Mapping(source = "balance",                    target = "saldo" )
    MovementDTO toMovementDTO(Movement movement);

    @InheritInverseConfiguration
    Movement toMovement(MovementDTO movementDTO);
}
