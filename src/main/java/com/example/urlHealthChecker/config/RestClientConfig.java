package com.example.urlHealthChecker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${app.health-check.request-timeout-ms:5000}")
    private int requestTimeoutMs;

    @Bean
    public RestClient healthCheckRestClient() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(requestTimeoutMs);
        factory.setReadTimeout(requestTimeoutMs);

        return RestClient.builder()
                .requestFactory(factory)
                .build();
    }
}