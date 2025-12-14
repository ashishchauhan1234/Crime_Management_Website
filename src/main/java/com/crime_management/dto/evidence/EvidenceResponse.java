package com.crime_management.dto.evidence;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EvidenceResponse {
    private Long id;
    private String evidenceType;
    private String filePath;
    private String description;
    private LocalDateTime uploadedAt;
}
