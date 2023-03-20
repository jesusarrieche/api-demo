package com.devsu.api.web.controllers;

import com.devsu.api.application.dtos.MovementDTO;
import com.devsu.api.application.validations.Create;
import com.devsu.api.domain.services.MovementService;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/movimientos")
public class MovementController {

    private MovementService movementService;

    public MovementController(MovementService movementService) {
        this.movementService = movementService;
    }
    @GetMapping("/{movementId}")
    public ResponseEntity<MovementDTO> getMovementById(@PathVariable @NotNull Long movementId) {
        log.info("GET - /movimientos/{}", movementId);

        return new ResponseEntity<>(this.movementService.findMovementById(movementId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MovementDTO> saveMovement(@Validated(Create.class) @RequestBody MovementDTO movementDTO) {
        log.info("POST - /movimientos");

        return new ResponseEntity<>(this.movementService.createMovement(movementDTO), HttpStatus.CREATED);
    }
    @DeleteMapping("/{movementId}")
    public ResponseEntity<?> deleteAccount(@PathVariable @NotNull Long movementId) {
        log.info("DELETE - /movimientos/{}", movementId);
        this.movementService.deleteMovement(movementId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
