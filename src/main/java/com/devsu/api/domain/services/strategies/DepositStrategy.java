package com.devsu.api.domain.services.strategies;

import com.devsu.api.application.dtos.MovementDTO;
import com.devsu.api.domain.entities.Account;
import com.devsu.api.domain.entities.Movement;
import com.devsu.api.domain.enums.MovementType;
import com.devsu.api.domain.repositories.MovementRepository;

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
     * @param account
     * @param movementDTO
     * @return
     */
    @Override
    public Movement update(Account account, MovementDTO movementDTO) {



        return null;
    }
}
