package com.crime_management.controller;

import com.crime_management.dto.cases.CaseCreateRequest;
import com.crime_management.dto.cases.CaseResponse;
import com.crime_management.service.CaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cases")
@RequiredArgsConstructor
public class CaseController {

    private final CaseService caseService;

    @PostMapping
    public ResponseEntity<CaseResponse> createCase(@RequestBody CaseCreateRequest request) {
        return ResponseEntity.ok(caseService.createCase(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaseResponse> getCase(@PathVariable Long id) {
        return ResponseEntity.ok(caseService.getCase(id));
    }

    @GetMapping
    public ResponseEntity<List<CaseResponse>> getCases() {
        return ResponseEntity.ok(caseService.getAllCases());
    }
}
