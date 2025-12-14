package com.crime_management.dto.crime;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CrimeResponse {
    private Long id;
    private String crimeType;
    private String location;
    private String description;
    private LocalDateTime occurredAt;
    private LocalDateTime createdAt;
}
