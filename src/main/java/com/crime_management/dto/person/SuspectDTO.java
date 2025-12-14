package com.crime_management.dto.person;

import lombok.Data;

@Data
public class SuspectDTO extends PersonDTO {
    private String criminalHistory;
}
