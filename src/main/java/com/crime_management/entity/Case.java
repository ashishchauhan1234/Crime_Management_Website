package com.crime_management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "cases")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "crime_id")
    private Crime crime;

    @ManyToOne
    @JoinColumn(name = "assigned_officer")
    private User assignedOfficer;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private CaseStatus status;

    private String summary;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();
}
