package com.devsu.api.domain.services;

import com.devsu.api.application.dtos.ClientDTO;
import com.devsu.api.application.exceptions.BadRequestException;
import com.devsu.api.application.mappers.ClientMapper;
import com.devsu.api.domain.entities.Client;
import com.devsu.api.domain.repositories.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;

class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientServiceImpl clientService;

    private ClientDTO clientDTO;


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        clientDTO = new ClientDTO();
        clientDTO.setIdentificacion("123456789");
        clientDTO.setNombres("Juan Perez");
        clientDTO.setContrasena("1245");
        clientDTO.setTelefono("098874587");
        clientDTO.setDireccion("13 junio y Equinoccial");
        clientDTO.setEstado(Boolean.TRUE);


    }

    @Test
    public void testCreateClient() {

        Client newClient = new Client();
        newClient.setPersonId(1L);
        newClient.setIdentification("123456789");
        newClient.setFullName("Juan Perez");
        newClient.setPhoneNumber("098874587");
        newClient.setAddress("13 junio y Equinoccial");
        newClient.setStatus(Boolean.TRUE);

        Mockito.when(clientRepository.findByIdentification("123456789")).thenReturn(Optional.empty());
        Mockito.when(clientMapper.toClient(clientDTO)).thenReturn(newClient);
        Mockito.when(clientRepository.save(newClient)).thenReturn(newClient);
        Mockito.when(clientMapper.toClientDTO(newClient)).thenReturn(clientDTO);



        ClientDTO createdClientDTO = clientService.createClient(clientDTO);


        assertNotNull(createdClientDTO);
        assertEquals(clientDTO.getIdentificacion(), createdClientDTO.getIdentificacion());
        assertEquals(clientDTO.getNombres(), createdClientDTO.getNombres());
        assertEquals(clientDTO.getDireccion(), createdClientDTO.getDireccion());
        assertEquals(clientDTO.getTelefono(), createdClientDTO.getTelefono());
        assertEquals(clientDTO.getContrasena(), createdClientDTO.getContrasena());
        assertEquals(clientDTO.getEstado(), createdClientDTO.getEstado());

        Mockito.verify(clientRepository, Mockito.times(1)).findByIdentification("123456789");
        Mockito.verify(clientRepository, Mockito.times(1)).save(newClient);
        Mockito.verify(clientMapper, Mockito.times(1)).toClient(clientDTO);
        Mockito.verify(clientMapper, Mockito.times(1)).toClientDTO(newClient);
    }

    @Test
    void testCreateClientWithExistingIdentification() {
        Mockito.when(clientRepository.findByIdentification(anyString())).thenReturn(Optional.of(new Client()));
        Assertions.assertThrows(BadRequestException.class, () -> clientService.createClient(clientDTO));
        Mockito.verify(clientRepository, Mockito.times(1)).findByIdentification(anyString());
        Mockito.verify(clientRepository, Mockito.never()).save(Mockito.any(Client.class));
    }

}