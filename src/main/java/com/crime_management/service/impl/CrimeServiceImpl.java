package com.crime_management.service.impl;

import com.crime_management.exception.ResourceNotFoundException;
import com.crime_management.service.CrimeService;
import com.crime_management.repository.UserRepository;
import com.crime_management.dto.crime.CrimeRequest;
import com.crime_management.dto.crime.CrimeResponse;
import com.crime_management.entity.Crime;
import com.crime_management.entity.CrimeLocation;
import com.crime_management.entity.CrimeType;
import com.crime_management.entity.User;
import com.crime_management.mapper.CrimeMapper;
import com.crime_management.repository.CrimeLocationRepository;
import com.crime_management.repository.CrimeRepository;
import com.crime_management.repository.CrimeTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CrimeServiceImpl implements CrimeService {

    private final CrimeRepository crimeRepository;
    private final CrimeTypeRepository crimeTypeRepository;
    private final CrimeLocationRepository crimeLocationRepository;
    private final UserRepository userRepository;
    private final CrimeMapper crimeMapper;

    @Override
    public CrimeResponse createCrime(CrimeRequest request, Long reportedBy) {

        CrimeType type = crimeTypeRepository.findById(request.getCrimeTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid crime type"));

        CrimeLocation location = crimeLocationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid location"));

        User user = userRepository.findById(reportedBy)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Crime crime = Crime.builder()
                .crimeType(type)
                .location(location)
                .description(request.getDescription())
                .occurredAt(request.getOccurredAt())
                .reportedBy(user)
                .build();

        crimeRepository.save(crime);

        return crimeMapper.toResponse(crime);
    }

    @Override
    public CrimeResponse getCrime(Long id) {
        Crime crime = crimeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Crime not found"));

        return crimeMapper.toResponse(crime);
    }

    @Override
    public List<CrimeResponse> getAllCrimes() {
        return crimeRepository.findAll()
                .stream()
                .map(crimeMapper::toResponse)
                .toList();
    }
}
