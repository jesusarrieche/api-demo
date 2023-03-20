package com.devsu.api.web.controllers;

import com.devsu.api.application.dtos.ClientDTO;
import com.devsu.api.application.validations.Create;
import com.devsu.api.application.validations.PartialUpdate;
import com.devsu.api.application.validations.Update;
import com.devsu.api.domain.services.ClientService;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/clientes")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{identification}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable @NotNull String identification) {
        log.info("GET - /clientes/{}", identification);

        return new ResponseEntity<>(this.clientService.findClientByIdentification(identification), HttpStatus.OK);
    }


    /**
     *
     * @param clientDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<ClientDTO> saveClient(@Validated(Create.class) @RequestBody ClientDTO clientDTO) {
        log.info("POST - /clientes");

        return new ResponseEntity<>(this.clientService.createClient(clientDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{identification}")
    public ResponseEntity<ClientDTO> updateClient(
            @PathVariable String identification,
            @Validated(Update.class) @RequestBody ClientDTO clientDTO
    ) {
        log.info("PUT - /clientes/{}", identification);

        return new ResponseEntity<>(this.clientService.updateClient(identification, clientDTO), HttpStatus.OK);
    }

    @PatchMapping("/{identification}")
    public ResponseEntity<ClientDTO> updateClientPassword(
            @PathVariable String identification,
            @Validated(PartialUpdate.class) @RequestBody ClientDTO clientDTO
    ) {
        log.info("PATCH - /clientes/{}", identification, clientDTO);

        return new ResponseEntity<>(this.clientService.updateClientPassword(identification, clientDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{identification}")
    public ResponseEntity<?> deleteClient(@PathVariable @NotNull String identification) {
        log.info("DELETE - /clientes/{}", identification);
        this.clientService.deleteClientByIdentification(identification);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
