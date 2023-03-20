package com.devsu.api.domain.services;

import com.devsu.api.application.dtos.MovementDTO;

public interface MovementService {
    MovementDTO createMovement(MovementDTO movementDTO);

    MovementDTO editMovement(Long movementId, MovementDTO movementDTO);

    void deleteMovement(Long movementId);
}
