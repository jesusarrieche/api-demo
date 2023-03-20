package com.devsu.api.domain.services.strategies;

import com.devsu.api.application.dtos.MovementDTO;
import com.devsu.api.domain.entities.Account;
import com.devsu.api.domain.entities.Movement;

public interface MovementStrategy {
    Movement create(Account account, MovementDTO movementDTO);

    void delete(Movement movement);
}
