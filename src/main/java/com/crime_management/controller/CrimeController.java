package com.crime_management.controller;

import com.crime_management.dto.crime.CrimeRequest;
import com.crime_management.dto.crime.CrimeResponse;
import com.crime_management.service.CrimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crimes")
@RequiredArgsConstructor
public class CrimeController {

    private final CrimeService crimeService;

    @PostMapping
    public ResponseEntity<CrimeResponse> createCrime(
            @RequestBody CrimeRequest request,
            @RequestHeader("X-USER-ID") Long userId // later replaced by JWT
    ) {
        return ResponseEntity.ok(crimeService.createCrime(request, userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CrimeResponse> getCrime(@PathVariable Long id) {
        return ResponseEntity.ok(crimeService.getCrime(id));
    }

    @GetMapping
    public ResponseEntity<List<CrimeResponse>> getCrimes() {
        return ResponseEntity.ok(crimeService.getAllCrimes());
    }
}
