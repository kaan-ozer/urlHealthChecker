package com.example.urlHealthChecker.service;

import com.example.urlHealthChecker.model.UrlRecord;
import com.example.urlHealthChecker.repository.UrlRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
@Slf4j
public class HealthCheckService {

    private final UrlRecordRepository repository;

    private final RestClient restClient;

    public void checkUrlHealth(UrlRecord record) {
        long startTime = System.currentTimeMillis();
        try {

            var response = restClient.get()
                    .uri(record.getUrl())
                    .retrieve()
                    .toBodilessEntity();

            int code = response.getStatusCode().value();
            long responseTime = System.currentTimeMillis() - startTime;

            record.setLastStatusCode(code);
            record.setLastResponseTime(responseTime);
            record.setLastCheckTime(LocalDateTime.now());

            log.info("URL: {} - Status: {}", record.getUrl(), code);
            saveToRedisAsync(record);

        } catch (Exception e) {
            log.error("URL kontrol hatası: {} - {}", record.getUrl(), e.getMessage());
            record.setLastStatusCode(500);
            saveToRedisAsync(record);
        }
    }

    @Async("redisWriteExecutor")
    public void saveToRedisAsync(UrlRecord record) {
        repository.save(record);
    }
}
