package com.crime_management.controller;

import com.crime_management.dto.evidence.EvidenceRequest;
import com.crime_management.dto.evidence.EvidenceResponse;
import com.crime_management.service.EvidenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/evidence")
@RequiredArgsConstructor
public class EvidenceController {

    private final EvidenceService evidenceService;

    @PostMapping("/upload")
    public ResponseEntity<EvidenceResponse> uploadEvidence(
            @RequestPart("data") EvidenceRequest request,
            @RequestPart("file") MultipartFile file,
            @RequestHeader("X-USER-ID") Long uploadedBy
    ) {
        String filePath = "/storage/" + file.getOriginalFilename(); // later replace with AWS S3 or FileSystem

        return ResponseEntity.ok(
                evidenceService.uploadEvidence(request, filePath, uploadedBy)
        );
    }
}
