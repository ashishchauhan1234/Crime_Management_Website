package com.crime_management.service;


import com.crime_management.dto.person.SuspectDTO;
import com.crime_management.dto.person.VictimDTO;
import com.crime_management.dto.person.WitnessDTO;

public interface PersonService {
    SuspectDTO addSuspect(SuspectDTO dto);
    VictimDTO addVictim(VictimDTO dto);
    WitnessDTO addWitness(WitnessDTO dto);
}
