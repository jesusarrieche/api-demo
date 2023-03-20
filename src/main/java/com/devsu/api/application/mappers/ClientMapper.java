package com.devsu.api.application.mappers;

import com.devsu.api.application.dtos.ClientDTO;
import com.devsu.api.domain.entities.Client;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ClientMapper {
    @Mapping(source = "identification", target = "identificacion")
    @Mapping(source = "fullName",       target = "nombres")
    @Mapping(source = "address",        target = "direccion")
    @Mapping(source = "phoneNumber",    target = "telefono")
    @Mapping(target = "contrasena",     ignore = true)
    @Mapping(source = "status",         target = "estado")
    ClientDTO toClientDTO(Client client);

    @InheritInverseConfiguration
    @Mapping(source = "contrasena", target = "password")
    Client toClient(ClientDTO clientDTO);

    @InheritInverseConfiguration
    @Mapping(source = "contrasena", target = "password")
    Client updateClientFromClientDTO(ClientDTO clientDTO, @MappingTarget Client client);
}
