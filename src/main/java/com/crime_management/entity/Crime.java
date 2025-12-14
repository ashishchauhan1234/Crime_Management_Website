package com.crime_management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "crimes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Crime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "crime_type_id")
    private CrimeType crimeType;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private CrimeLocation location;

    @ManyToOne
    @JoinColumn(name = "reported_by")
    private User reportedBy;

    private String description;

    private LocalDateTime occurredAt;

    private LocalDateTime createdAt = LocalDateTime.now();
}
