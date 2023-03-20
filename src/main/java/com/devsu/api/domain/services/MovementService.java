package com.devsu.api.domain.services;

import com.devsu.api.application.dtos.MovementDTO;

public interface MovementService {

    MovementDTO findMovementById(Long movementId);
    MovementDTO createMovement(MovementDTO movementDTO);

    void deleteMovement(Long movementId);
}
