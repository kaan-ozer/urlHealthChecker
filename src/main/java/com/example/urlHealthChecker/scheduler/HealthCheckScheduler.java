package com.example.urlHealthChecker.scheduler;

import com.example.urlHealthChecker.model.UrlRecord;
import com.example.urlHealthChecker.service.HealthCheckService;
import com.example.urlHealthChecker.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Executor;

@Component
@RequiredArgsConstructor
public class HealthCheckScheduler {

    private final UrlService urlService;
    private final HealthCheckService healthCheckService;

    @Qualifier("healthCheckExecutor")
    private final Executor healthCheckExecutor;

    @Scheduled(fixedRateString = "${app.scheduler.rate:30000}")
    public void runHealthChecks() {
        List<UrlRecord> urls = urlService.getAllUrls();

        if (urls.isEmpty()) {
            return; //kontrol edilecek bir durum yoksa, thread kullanılmaması için.
        }

        for (UrlRecord url : urls) {
            healthCheckExecutor.execute(() -> healthCheckService.checkUrlHealth(url));
        }
    }
}
