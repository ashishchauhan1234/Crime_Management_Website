package com.crime_management.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "victims")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
public class Victim extends Person {

    private String injuryDescription;
}
