package com.crime_management.service.impl;

import com.crime_management.exception.ResourceNotFoundException;
import com.crime_management.service.EvidenceService;
import com.crime_management.repository.UserRepository;
import com.crime_management.dto.evidence.EvidenceRequest;
import com.crime_management.dto.evidence.EvidenceResponse;
import com.crime_management.entity.Case;
import com.crime_management.entity.Evidence;
import com.crime_management.entity.User;
import com.crime_management.mapper.EvidenceMapper;
import com.crime_management.repository.CaseRepository;
import com.crime_management.repository.EvidenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvidenceServiceImpl implements EvidenceService {

    private final EvidenceRepository evidenceRepository;
    private final CaseRepository caseRepository;
    private final UserRepository userRepository;
    private final EvidenceMapper evidenceMapper;

    @Override
    public EvidenceResponse uploadEvidence(EvidenceRequest request, String filePath, Long uploadedBy) {

        Case caseObj = caseRepository.findById(request.getCaseId())
                .orElseThrow(() -> new ResourceNotFoundException("Case not found"));

        User user = userRepository.findById(uploadedBy)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Evidence evidence = Evidence.builder()
                .caseRef(caseObj)
                .evidenceType(request.getEvidenceType())
                .description(request.getDescription())
                .filePath(filePath)
                .uploadedBy(user)
                .build();

        evidenceRepository.save(evidence);

        return evidenceMapper.toResponse(evidence);
    }
}
