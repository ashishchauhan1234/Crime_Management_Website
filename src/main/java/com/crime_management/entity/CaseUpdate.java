package com.crime_management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "case_updates")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CaseUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "case_id")
    private Case caseRef;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private User updatedBy;

    private String updateNote;

    private LocalDateTime updatedAt = LocalDateTime.now();
}
