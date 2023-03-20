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

    private final BigDecimal max = new BigDecimal(1000);


    public MovementServiceImpl(MovementRepository movementRepository, AccountRepository accountRepository, MovementMapper movementMapper) {
        this.movementRepository = movementRepository;
        this.accountRepository = accountRepository;
        this.movementMapper = movementMapper;
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

        MovementStrategy strategy = this.getStrategy(movementDTO);

        Movement newMovement = strategy.create(account, movementDTO);

        return this.movementMapper.toMovementDTO(newMovement);
    }

    /**
     * @param movementId
     * @param movementDTO
     * @return
     */
    @Override
    @Transactional
    public MovementDTO editMovement(Long movementId, MovementDTO movementDTO) {



        return null;
    }

    /**
     * @param movementId
     */
    @Override
    public void deleteMovement(Long movementId) {

    }
    private MovementStrategy getStrategy(MovementDTO movementDTO) {
        if (movementDTO.getMonto().compareTo(BigDecimal.ZERO) < 0) {
            return new WithdrawalStrategy(this.movementRepository);
        } else {
            return new DepositStrategy(this.movementRepository);
        }
    }
}
