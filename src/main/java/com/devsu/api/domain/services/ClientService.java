package com.devsu.api.domain.services;

import com.devsu.api.application.dtos.ClientDTO;

public interface ClientService {

    public ClientDTO findClientByIdentification(String identification);
    public ClientDTO createClient(ClientDTO clientDTO);
    public ClientDTO updateClient(String identification, ClientDTO clientDTO);
    public ClientDTO updateClientPassword(String identification, ClientDTO clientDTO);
    public void deleteClientByIdentification(String clientId);

}
