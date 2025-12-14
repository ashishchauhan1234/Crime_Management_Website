package com.crime_management.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "suspects")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
public class Suspect extends Person {

    private String criminalHistory;
}
