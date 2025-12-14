package com.crime_management.service;


import com.crime_management.dto.evidence.EvidenceRequest;
import com.crime_management.dto.evidence.EvidenceResponse;

public interface EvidenceService {
    EvidenceResponse uploadEvidence(EvidenceRequest request, String filePath, Long uploadedBy);
}
