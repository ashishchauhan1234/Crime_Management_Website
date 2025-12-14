package com.crime_management.controller;

import com.crime_management.dto.person.SuspectDTO;
import com.crime_management.dto.person.VictimDTO;
import com.crime_management.dto.person.WitnessDTO;
import com.crime_management.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping("/suspects")
    public ResponseEntity<SuspectDTO> addSuspect(@RequestBody SuspectDTO dto) {
        return ResponseEntity.ok(personService.addSuspect(dto));
    }

    @PostMapping("/victims")
    public ResponseEntity<VictimDTO> addVictim(@RequestBody VictimDTO dto) {
        return ResponseEntity.ok(personService.addVictim(dto));
    }

    @PostMapping("/witnesses")
    public ResponseEntity<WitnessDTO> addWitness(@RequestBody WitnessDTO dto) {
        return ResponseEntity.ok(personService.addWitness(dto));
    }
}
