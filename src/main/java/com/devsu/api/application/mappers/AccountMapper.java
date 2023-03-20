package com.devsu.api.application.mappers;

import com.devsu.api.application.dtos.AccountDTO;
import com.devsu.api.domain.entities.Account;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface AccountMapper {

    @Mapping(source = "accountNumber", target = "numeroCuenta")
    @Mapping(source = "accountType", target = "tipo")
    @Mapping(source = "balance", target = "saldo")
    @Mapping(source = "status", target = "estado")
    @Mapping(source = "client.identification", target = "identificacionCliente")
    AccountDTO toAccountDTO(Account account);

    @InheritInverseConfiguration
    Account toAccount(AccountDTO accountDTO);

    @InheritInverseConfiguration
    Account updateAccountFromAccountDTO(AccountDTO accountDTO, @MappingTarget Account account);
}
