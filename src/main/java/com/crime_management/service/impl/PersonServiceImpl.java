package com.crime_management.service.impl;

import com.crime_management.service.PersonService;
import com.crime_management.dto.person.SuspectDTO;
import com.crime_management.dto.person.VictimDTO;
import com.crime_management.dto.person.WitnessDTO;
import com.crime_management.entity.Suspect;
import com.crime_management.entity.Victim;
import com.crime_management.entity.Witness;
import com.crime_management.mapper.PersonMapper;
import com.crime_management.repository.SuspectRepository;
import com.crime_management.repository.VictimRepository;
import com.crime_management.repository.WitnessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final SuspectRepository suspectRepository;
    private final VictimRepository victimRepository;
    private final WitnessRepository witnessRepository;
    private final PersonMapper personMapper;

    @Override
    public SuspectDTO addSuspect(SuspectDTO dto) {
        Suspect suspect = Suspect.builder()
                .name(dto.getName())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .criminalHistory(dto.getCriminalHistory())
                .build();

        suspectRepository.save(suspect);
        return personMapper.toSuspectDto(suspect);
    }

    @Override
    public VictimDTO addVictim(VictimDTO dto) {
        Victim victim = Victim.builder()
                .name(dto.getName())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .injuryDescription(dto.getInjuryDescription())
                .build();

        victimRepository.save(victim);
        return personMapper.toVictimDto(victim);
    }

    @Override
    public WitnessDTO addWitness(WitnessDTO dto) {
        Witness witness = Witness.builder()
                .name(dto.getName())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .statement(dto.getStatement())
                .build();

        witnessRepository.save(witness);
        return personMapper.toWitnessDto(witness);
    }
}
