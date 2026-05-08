package com.example.urlHealthChecker.controller;

import com.example.urlHealthChecker.dto.UrlCreateRequestDto;
import com.example.urlHealthChecker.dto.UrlResponseDto;
import com.example.urlHealthChecker.model.UrlRecord;
import com.example.urlHealthChecker.service.UrlService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/urls")
@RequiredArgsConstructor
@Tag(name = "URL Health Check", description = "URL ekleme ve url saglik durumlarini izleme servisleri")
public class UrlController {

    private final UrlService urlService;

    @PostMapping
    public ResponseEntity<UrlRecord> addUrl(@Valid @RequestBody UrlCreateRequestDto request) {
        return ResponseEntity.ok(urlService.addUrl(request));
    }

    @GetMapping
    public ResponseEntity<List<UrlResponseDto>> getAllUrls() {
        List<UrlResponseDto> responses = urlService.getAllUrls().stream()
                .map(urlService::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}
