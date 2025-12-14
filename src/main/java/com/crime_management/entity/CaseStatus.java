package com.crime_management.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "case_status")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CaseStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;
}
