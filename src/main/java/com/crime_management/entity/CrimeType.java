package com.crime_management.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "crime_types")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CrimeType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
