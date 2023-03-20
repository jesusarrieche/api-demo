package com.devsu.api.domain.services;

import com.devsu.api.application.dtos.ClientDTO;
import com.devsu.api.application.exceptions.BadRequestException;
import com.devsu.api.application.exceptions.NotFoundException;
import com.devsu.api.application.mappers.ClientMapper;
import com.devsu.api.domain.entities.Client;
import com.devsu.api.domain.repositories.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    private ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    /**
     * @param identification
     * @return
     */
    @Override
    public ClientDTO findClientByIdentification(String identification) {

        Client storedClient = this.clientRepository.findByIdentification(identification)
                .orElseThrow(
                        () -> new NotFoundException(String.format("No se encontro Cliente con indentificacion: %s", identification))
                );

        return this.clientMapper.toClientDTO(storedClient);
    }

    /**
     * @param clientDTO
     * @return
     */
    @Override
    @Transactional
    public ClientDTO createClient(ClientDTO clientDTO) {

        this.clientRepository.findByIdentification(clientDTO.getIdentificacion())
                .ifPresent((e) -> {
                    throw new BadRequestException(String.format("Ya existe un cliente con la Identificacion: %s", clientDTO.getIdentificacion()));
                });

        Client newClient = this.clientMapper.toClient(clientDTO);

        newClient = this.clientRepository.save(newClient);

        return this.clientMapper.toClientDTO(newClient);
    }

    /**
     * @param clientDTO
     * @return
     */
    @Override
    @Transactional
    public ClientDTO updateClient(String identification, ClientDTO clientDTO) {

        Client storedClient = this.clientRepository.findByIdentification(identification)
                .orElseThrow(
                        () -> new NotFoundException(String.format("No se encontro Cliente con Identification: %s", identification))
                );

        this.clientRepository.findByIdentificationAndPersonIdNot(clientDTO.getIdentificacion(), storedClient.getPersonId())
                .ifPresent(
                        (e) -> {
                            throw new BadRequestException(String.format("Ya existe otro usuario con la Identificacion: %s", clientDTO.getIdentificacion()));
                        }
                );

        // update
        this.clientMapper.updateClientFromClientDTO(clientDTO, storedClient);

        return this.clientMapper.toClientDTO(this.clientRepository.save(storedClient));
    }

    /**
     * @param identification
     */
    @Override
    public void deleteClientByIdentification(String identification) {
        Client toDelete = this.clientRepository.findByIdentification(identification)
                .orElseThrow(
                        () -> new NotFoundException(String.format("No se encontro Cliente con Identification: %s", identification))
                );

        this.clientRepository.deleteById(toDelete.getPersonId());
    }
}
