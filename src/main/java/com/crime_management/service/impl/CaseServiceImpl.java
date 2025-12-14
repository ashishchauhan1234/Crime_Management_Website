package com.crime_management.service.impl;

import com.crime_management.exception.ResourceNotFoundException;
import com.crime_management.service.CaseService;
import com.crime_management.repository.UserRepository;
import com.crime_management.dto.cases.CaseCreateRequest;
import com.crime_management.dto.cases.CaseResponse;
import com.crime_management.entity.Case;
import com.crime_management.entity.CaseStatus;
import com.crime_management.entity.Crime;
import com.crime_management.entity.User;
import com.crime_management.mapper.CaseMapper;
import com.crime_management.repository.CaseRepository;
import com.crime_management.repository.CaseStatusRepository;
import com.crime_management.repository.CrimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CaseServiceImpl implements CaseService {

    private final CaseRepository caseRepository;
    private final CrimeRepository crimeRepository;
    private final UserRepository userRepository;
    private final CaseStatusRepository caseStatusRepository;
    private final CaseMapper caseMapper;

    @Override
    public CaseResponse createCase(CaseCreateRequest request) {

        Crime crime = crimeRepository.findById(request.getCrimeId())
                .orElseThrow(() -> new ResourceNotFoundException("Crime not found"));

        User officer = userRepository.findById(request.getOfficerId())
                .orElseThrow(() -> new ResourceNotFoundException("Officer not found"));

        CaseStatus status = caseStatusRepository.findById(1L)  // OPEN
                .orElseThrow(() -> new ResourceNotFoundException("Case status missing"));

        Case caseObj = Case.builder()
                .crime(crime)
                .assignedOfficer(officer)
                .summary(request.getSummary())
                .status(status)
                .build();

        caseRepository.save(caseObj);

        return caseMapper.toResponse(caseObj);
    }

    @Override
    public CaseResponse getCase(Long id) {
        Case caseObj = caseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Case not found"));

        return caseMapper.toResponse(caseObj);
    }

    @Override
    public List<CaseResponse> getAllCases() {
        return caseRepository.findAll()
                .stream()
                .map(caseMapper::toResponse)
                .toList();
    }
}
