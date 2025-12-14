package com.crime_management.dto.crime;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CrimeRequest {
    private Long crimeTypeId;
    private Long locationId;
    private String description;
    private LocalDateTime occurredAt;
}
