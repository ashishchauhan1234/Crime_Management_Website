package com.crime_management.mapper;

import com.crime_management.dto.person.SuspectDTO;
import com.crime_management.dto.person.VictimDTO;
import com.crime_management.dto.person.WitnessDTO;
import com.crime_management.entity.Suspect;
import com.crime_management.entity.Victim;
import com.crime_management.entity.Witness;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapperConfig.class)
public interface PersonMapper {

    SuspectDTO toSuspectDto(Suspect suspect);

    VictimDTO toVictimDto(Victim victim);

    WitnessDTO toWitnessDto(Witness witness);
}
