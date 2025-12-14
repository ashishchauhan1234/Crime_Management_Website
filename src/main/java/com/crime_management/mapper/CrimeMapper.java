package com.crime_management.mapper;

import com.crime_management.dto.crime.CrimeResponse;
import com.crime_management.entity.Crime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = com.crime_management.mapper.GlobalMapperConfig.class)
public interface CrimeMapper {

    @Mapping(target = "crimeType", source = "crimeType.name")
    @Mapping(target = "location", source = "location.address")
    CrimeResponse toResponse(Crime crime);
}
