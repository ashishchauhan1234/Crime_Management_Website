package com.crime_management.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "persons")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    private String address;

    private LocalDateTime createdAt = LocalDateTime.now();
}
