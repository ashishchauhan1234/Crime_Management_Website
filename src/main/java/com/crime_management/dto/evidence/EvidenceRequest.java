package com.crime_management.dto.evidence;

import lombok.Data;

@Data
public class EvidenceRequest {
    private Long caseId;
    private String evidenceType;
    private String description;
}
