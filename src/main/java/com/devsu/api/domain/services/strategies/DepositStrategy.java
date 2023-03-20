package com.devsu.api.domain.services.strategies;

import com.devsu.api.application.dtos.MovementDTO;
import com.devsu.api.application.exceptions.BadRequestException;
import com.devsu.api.domain.entities.Account;
import com.devsu.api.domain.entities.Movement;
import com.devsu.api.domain.enums.MovementType;
import com.devsu.api.domain.repositories.MovementRepository;

import java.math.BigDecimal;

public class DepositStrategy implements MovementStrategy {

    private final MovementRepository movementRepository;

    public DepositStrategy(MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    /**
     * @param account
     * @param movementDTO
     * @return
     */
    @Override
    public Movement create(Account account, MovementDTO movementDTO) {

        Movement movement = new Movement();
        movement.setDate(movementDTO.getFecha());
        movement.setMovementType(MovementType.DEPOSITO);
        movement.setAmount(movementDTO.getMonto());
        movement.setBalance(account.getBalance().add(movementDTO.getMonto()));

        account.setBalance(account.getBalance().add(movementDTO.getMonto()));
        movement.setAccount(account);

        return this.movementRepository.save(movement);
    }

    /**
     * @param movement
     */
    @Override
    public void delete(Movement movement) {

        Account account = movement.getAccount();

        BigDecimal newBalance = account.getBalance().subtract(movement.getAmount());

        if(newBalance.compareTo(BigDecimal.ZERO) < 0)
            throw new BadRequestException(
                    String.format(
                            "No puede eliminar este deposito con ID: %s y monto: %s, no se permiten saldos negativos en la cuenta. " +
                                    "Saldo actual: %s", movement.getId(), movement.getAmount(), account.getBalance()
                    )
            );

        account.setBalance(newBalance);
        this.movementRepository.save(movement);

        this.movementRepository.delete(movement);

    }


}
