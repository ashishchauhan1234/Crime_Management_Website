package com.crime_management.dto.cases;

import lombok.Data;

@Data
public class CaseResponse {
    private Long id;
    private String status;
    private String summary;
    private String assignedOfficer;
    private String crimeDescription;
}
