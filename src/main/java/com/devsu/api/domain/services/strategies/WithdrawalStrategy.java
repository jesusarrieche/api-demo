package com.devsu.api.domain.services.strategies;

import com.devsu.api.application.dtos.MovementDTO;
import com.devsu.api.application.exceptions.BadRequestException;
import com.devsu.api.domain.entities.Account;
import com.devsu.api.domain.entities.Movement;
import com.devsu.api.domain.enums.MovementType;
import com.devsu.api.domain.repositories.MovementRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

public class WithdrawalStrategy implements MovementStrategy {

    private final MovementRepository movementRepository;
    private final BigDecimal withdrawalLimit = new BigDecimal(1000);

    public WithdrawalStrategy(MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    /**
     * @param account
     * @param movementDTO
     * @return
     */
    @Override
    public Movement create(Account account, MovementDTO movementDTO) {

        BigDecimal newBalance = account.getBalance().add(movementDTO.getMonto());

        if (newBalance.compareTo(BigDecimal.ZERO) < 0)
            throw new BadRequestException(String.format("Saldo insuficiente. Su balance actual es de: %s", account.getBalance()));

        BigDecimal totalWithdrawalAmount = this.movementRepository.getTotalWithdrawalAmountByDayAndAccountNumber(movementDTO.getFecha(), account.getAccountNumber());

        if(totalWithdrawalAmount.abs().compareTo(this.withdrawalLimit) >= 0)
            throw new BadRequestException(String.format("Cupo diario Excedido. Cantidad disponible: %s", this.withdrawalLimit.add(totalWithdrawalAmount)));



        Movement movement = new Movement();
        movement.setDate(movementDTO.getFecha());
        movement.setMovementType(MovementType.RETIRO);
        movement.setAmount(movementDTO.getMonto());
        movement.setBalance(newBalance);

        account.setBalance(newBalance);
        movement.setAccount(account);

        return this.movementRepository.save(movement);
    }

    /**
     * @param account
     * @param movementDTO
     * @return
     */
    @Override
    public Movement update(Account account, MovementDTO movementDTO) {
        return null;
    }
}
