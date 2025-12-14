package com.crime_management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "case_assignments")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CaseAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "case_id")
    private Case caseRef;

    @ManyToOne
    @JoinColumn(name = "officer_id")
    private User officer;

    private LocalDateTime assignedAt = LocalDateTime.now();
}
