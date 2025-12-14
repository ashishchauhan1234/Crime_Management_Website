package com.crime_management.service;

import com.crime_management.dto.crime.CrimeRequest;
import com.crime_management.dto.crime.CrimeResponse;

import java.util.List;

public interface CrimeService {
    CrimeResponse createCrime(CrimeRequest request, Long reportedBy);
    CrimeResponse getCrime(Long id);
    List<CrimeResponse> getAllCrimes();
}
