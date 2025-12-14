package com.crime_management.mapper;

import com.crime_management.dto.evidence.EvidenceResponse;
import com.crime_management.entity.Evidence;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapperConfig.class)
public interface EvidenceMapper {

    EvidenceResponse toResponse(Evidence evidence);
}
