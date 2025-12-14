package com.crime_management.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "case_witnesses")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CaseWitness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "case_id")
    private Case caseRef;

    @ManyToOne
    @JoinColumn(name = "witness_id")
    private Witness witness;
}
