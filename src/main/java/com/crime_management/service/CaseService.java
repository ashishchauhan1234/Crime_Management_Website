package com.crime_management.service;

import com.crime_management.dto.cases.CaseCreateRequest;
import com.crime_management.dto.cases.CaseResponse;

import java.util.List;

public interface CaseService {
    CaseResponse createCase(CaseCreateRequest request);
    CaseResponse getCase(Long id);
    List<CaseResponse> getAllCases();
}
