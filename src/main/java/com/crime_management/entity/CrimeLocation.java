package com.crime_management.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "crime_locations")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CrimeLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private Double latitude;

    private Double longitude;
}
