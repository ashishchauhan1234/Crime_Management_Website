package com.crime_management.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "case_victims")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CaseVictim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "case_id")
    private Case caseRef;

    @ManyToOne
    @JoinColumn(name = "victim_id")
    private Victim victim;
}
