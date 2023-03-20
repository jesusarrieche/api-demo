package com.devsu.api.domain.services;

import com.devsu.api.application.dtos.MovementDTO;
import com.devsu.api.application.exceptions.NotFoundException;
import com.devsu.api.application.mappers.MovementMapper;
import com.devsu.api.domain.entities.Account;
import com.devsu.api.domain.entities.Movement;
import com.devsu.api.domain.repositories.AccountRepository;
import com.devsu.api.domain.repositories.MovementRepository;
import com.devsu.api.domain.services.strategies.DepositStrategy;
import com.devsu.api.domain.services.strategies.MovementStrategy;
import com.devsu.api.domain.services.strategies.WithdrawalStrategy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class MovementServiceImpl implements MovementService {

    private MovementRepository movementRepository;
    private AccountRepository accountRepository;

    private MovementMapper movementMapper;

    public MovementServiceImpl(MovementRepository movementRepository, AccountRepository accountRepository, MovementMapper movementMapper) {
        this.movementRepository = movementRepository;
        this.accountRepository = accountRepository;
        this.movementMapper = movementMapper;
    }

    /**
     * @param movementId
     * @return
     */
    @Override
    public MovementDTO findMovementById(Long movementId) {
        return this.movementMapper.toMovementDTO(getMovement(movementId));
    }

    /**
     * @param movementDTO
     * @return
     */
    @Override
    @Transactional
    public MovementDTO createMovement(MovementDTO movementDTO) {

        Account account = this.accountRepository.findByAccountNumber(movementDTO.getNumeroCuenta())
                .orElseThrow(() -> new NotFoundException(String.format("No se encontro cuenta con numero: %s", movementDTO.getNumeroCuenta())));

        MovementStrategy strategy = this.getStrategy(movementDTO.getMonto());

        Movement newMovement = strategy.create(account, movementDTO);

        return this.movementMapper.toMovementDTO(newMovement);
    }


    /**
     * @param movementId
     */
    @Override
    public void deleteMovement(Long movementId) {

        Movement movement = getMovement(movementId);

        MovementStrategy strategy = getStrategy(movement.getAmount());
        strategy.delete(movement);

    }

    private MovementStrategy getStrategy(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            return new WithdrawalStrategy(this.movementRepository);
        } else {
            return new DepositStrategy(this.movementRepository);
        }
    }

    private Movement getMovement(Long movementId) {
        Movement movement = this.movementRepository.findById(movementId)
                .orElseThrow(() -> new NotFoundException(String.format("No se encontro movimiento con ID: %s", movementId)));
        return movement;
    }
}
