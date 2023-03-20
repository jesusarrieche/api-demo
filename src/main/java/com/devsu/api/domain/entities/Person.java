package com.devsu.api.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "persons")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personId")
    private Long personId;

    @Column(name = "full_name", nullable = false, length = 255)
    private String fullName;

    @Column(nullable = true, length = 10)
    private String gender;

    @Column(name = "age", columnDefinition = "SMALLINT")
    private Integer age;

    @Column(nullable = false, unique = true, length = 255)
    private String identification;

    @Column(nullable = true, length = 255)
	private String address;

    @Column(name = "phone_number", nullable = true, length = 50)
    private String phoneNumber;

}
