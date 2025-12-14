package com.crime_management.mapper;

import com.crime_management.dto.cases.CaseResponse;
import com.crime_management.entity.Case;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GlobalMapperConfig.class)
public interface CaseMapper {

    @Mapping(target = "status", source = "status.status")
    @Mapping(target = "assignedOfficer", source = "assignedOfficer.fullName")
    @Mapping(target = "crimeDescription", source = "crime.description")
    CaseResponse toResponse(Case caseObj);
}
