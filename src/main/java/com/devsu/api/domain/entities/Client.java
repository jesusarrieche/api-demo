package com.devsu.api.domain.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "clients")
@PrimaryKeyJoinColumn(name = "client_id")
public class Client extends Person {

    @Column(nullable = false, length = 255)
    private String password;

    private Boolean status;

    @OneToMany(mappedBy = "client")
    private List<Account> accounts;


}
