package com.devsu.api.domain.services;

import com.devsu.api.application.dtos.AccountDTO;
import com.devsu.api.application.exceptions.BadRequestException;
import com.devsu.api.application.exceptions.NotFoundException;
import com.devsu.api.application.mappers.AccountMapper;
import com.devsu.api.domain.entities.Account;
import com.devsu.api.domain.entities.Client;
import com.devsu.api.domain.repositories.AccountRepository;
import com.devsu.api.domain.repositories.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private ClientRepository clientRepository;
    private AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, ClientRepository clientRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
        this.accountMapper = accountMapper;
    }

    /**
     * @param accountNumber
     * @return
     */
    @Override
    public AccountDTO findAcocountByAcocuntNumber(String accountNumber) {
        Account storedAccount = this.accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(
                        () -> new NotFoundException(String.format("No se encontro cuenta con con numero: %s", accountNumber))
                );

        return this.accountMapper.toAccountDTO(storedAccount);
    }

    /**
     * @param accountDTO
     * @return
     */
    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        this.accountRepository.findByAccountNumber(accountDTO.getNumeroCuenta())
                .ifPresent((e) -> {
                    throw new BadRequestException(String.format("Ya existe una cuenta con el numero: %s", accountDTO.getNumeroCuenta()));
                });

        Client storedClient = this.clientRepository.findByIdentification(accountDTO.getIdentificacionCliente())
                .orElseThrow(
                        () -> new NotFoundException(String.format("No se encontro Cliente con indentificacion: %s", accountDTO.getIdentificacionCliente()))
                );

        Account newAccount = this.accountMapper.toAccount(accountDTO);
        newAccount.setClient(storedClient);

        return this.accountMapper.toAccountDTO(this.accountRepository.save(newAccount));
    }

    /**
     * @param accountNumber
     * @param accountDTO
     * @return
     */
    @Override
    public AccountDTO updateAccount(String accountNumber, AccountDTO accountDTO) {
        Account storedAccount = this.accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(
                        () -> new NotFoundException(String.format("No se encontro cuenta con con numero: %s", accountNumber))
                );

        this.accountRepository.findByAccountNumberAndIdNot(accountDTO.getNumeroCuenta(), storedAccount.getId())
                .ifPresent(
                        (e) -> {
                            throw new BadRequestException(String.format("Ya existe una cuenta con el numero: %s", accountDTO.getNumeroCuenta()));
                        }
                );

        // update
        this.accountMapper.updateAccountFromAccountDTO(accountDTO, storedAccount);

        return this.accountMapper.toAccountDTO(this.accountRepository.save(storedAccount));
    }

    /**
     * @param accountNumber
     */
    @Override
    public void deleteAccountByAccountNumber(String accountNumber) {
        Account toDelete = this.accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(
                        () -> new NotFoundException(String.format("No se encontro cuenta con con numero: %s", accountNumber))
                );

        this.accountRepository.deleteById(toDelete.getId());
    }
}
