package com.devsu.api.domain.repositories;

import com.devsu.api.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByIdentification(String identification);

    Optional<Client> findByIdentificationAndPersonIdNot(String identification, Long id);
}
