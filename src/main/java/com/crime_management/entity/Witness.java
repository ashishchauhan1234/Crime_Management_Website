package com.crime_management.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "witnesses")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
public class Witness extends Person {

    private String statement;
}
