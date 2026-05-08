package com.example.urlHealthChecker.service;

import com.example.urlHealthChecker.dto.UrlCreateRequestDto;
import com.example.urlHealthChecker.dto.UrlResponseDto;
import com.example.urlHealthChecker.model.UrlRecord;
import com.example.urlHealthChecker.repository.UrlRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRecordRepository urlRecordRepository;

    public UrlRecord addUrl(UrlCreateRequestDto request) {
        UrlRecord record = new UrlRecord();
        record.setId(request.getUrl());
        record.setUrl(request.getUrl());
        return urlRecordRepository.save(record);
    }

    public List<UrlRecord> getAllUrls() {
        return StreamSupport
                .stream(urlRecordRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }

    public UrlResponseDto mapToResponse(UrlRecord record) {
        return UrlResponseDto.builder()
                .url(record.getUrl())
                .lastStatusCode(record.getLastStatusCode())
                .lastResponseTime(record.getLastResponseTime())
                .lastCheckTime(record.getLastCheckTime() != null ? record.getLastCheckTime().toString() : "Henüz kontrol edilmedi")
                .build();
    }
}
