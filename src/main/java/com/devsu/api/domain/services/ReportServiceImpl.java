package com.devsu.api.domain.services;

import com.devsu.api.application.dtos.reports.AccountStatementDTO;
import com.devsu.api.application.dtos.reports.AccountStatementParams;
import com.devsu.api.application.dtos.reports.AccountSummary;
import com.devsu.api.application.dtos.reports.MovementsSummary;
import com.devsu.api.application.exceptions.NotFoundException;
import com.devsu.api.domain.entities.Client;
import com.devsu.api.domain.repositories.ClientRepository;
import com.devsu.api.domain.repositories.MovementRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private MovementRepository movementRepository;
    private ClientRepository clientRepository;

    public ReportServiceImpl(MovementRepository movementRepository, ClientRepository clientRepository) {
        this.movementRepository = movementRepository;
        this.clientRepository = clientRepository;
    }

    /**
     * @param params
     * @return
     */
    @Override
    public AccountStatementDTO generateAccountReport(AccountStatementParams params) {

        Client client = this.clientRepository.findByIdentification(params.getIdentificacionCliente()).orElseThrow(
                () -> new NotFoundException(String.format("No se encontro Cliente con Identification: %s", params.getIdentificacionCliente()))
        );

        List<AccountSummary> accountSummaries = new ArrayList<>();

        client.getAccounts().stream().forEach(account -> {

            AccountSummary accountSummary = new AccountSummary();

            params.setNumeroCuenta(account.getAccountNumber());
            MovementsSummary deposits = this.movementRepository.getMovementsSummary(params, 0);
            MovementsSummary withdrawals = this.movementRepository.getMovementsSummary(params, 1);

            accountSummary.setNumeroCuenta(account.getAccountNumber());
            accountSummary.setTipo(account.getAccountType());
            accountSummary.setSaldo(account.getBalance());
            accountSummary.setDebitos(withdrawals.getTotalMovements());
            accountSummary.setCreditos(deposits.getTotalMovements());
            accountSummary.setTotalDebito(withdrawals.getTotalAmount());
            accountSummary.setTotalCredito(deposits.getTotalAmount());

            accountSummaries.add(accountSummary);
        });

        AccountStatementDTO accountStatementDTO = new AccountStatementDTO();
        accountStatementDTO.setCliente(client.getIdentification() + " - " + client.getFullName());
        accountStatementDTO.setCuentas(accountSummaries);

        return accountStatementDTO;
    }
}
