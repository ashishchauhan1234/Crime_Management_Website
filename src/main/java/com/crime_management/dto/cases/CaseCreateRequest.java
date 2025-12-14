package com.crime_management.dto.cases;

import lombok.Data;

@Data
public class CaseCreateRequest {
    private Long crimeId;
    private Long officerId;
    private String summary;
}
