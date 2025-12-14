package com.crime_management.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "case_suspects")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CaseSuspect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "case_id")
    private Case caseRef;

    @ManyToOne
    @JoinColumn(name = "suspect_id")
    private Suspect suspect;
}
