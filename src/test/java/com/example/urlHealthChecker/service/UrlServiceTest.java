package com.example.urlHealthChecker.service;

import com.example.urlHealthChecker.dto.UrlCreateRequestDto;
import com.example.urlHealthChecker.model.UrlRecord;
import com.example.urlHealthChecker.repository.UrlRecordRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {

    @Mock
    private UrlRecordRepository urlRecordRepository;

    @InjectMocks
    private UrlService urlService;

    @Test
    @DisplayName("Yeni URL eklendiğinde repository'ye kaydedilmeli ve geri dönmeli")
    void addUrlTest() {

        UrlCreateRequestDto request = new UrlCreateRequestDto();
        request.setUrl("https://google.com");
        UrlRecord mockRecord = new UrlRecord();
        mockRecord.setUrl("https://google.com");

        when(urlRecordRepository.save(any(UrlRecord.class))).thenReturn(mockRecord);

        UrlRecord result = urlService.addUrl(request);

        assertNotNull(result);
        assertEquals("https://google.com", result.getUrl());
        verify(urlRecordRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Tüm URL'ler çekilmek istendiğinde liste dönmeli")
    void getAllUrlsTest() {
        when(urlRecordRepository.findAll()).thenReturn(List.of(new UrlRecord(), new UrlRecord()));
        List<UrlRecord> result = urlService.getAllUrls();
        assertEquals(2, result.size());
    }
}
