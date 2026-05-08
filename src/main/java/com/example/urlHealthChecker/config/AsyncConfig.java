package com.example.urlHealthChecker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Value("${app.thread.health-check-size:10}")
    private int healthPoolSize;

    @Value("${app.thread.redis-write-size:5}")
    private int redisPoolSize;

    @Bean(name = "healthCheckExecutor")
    public Executor healthCheckExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(healthPoolSize);
        executor.setMaxPoolSize(healthPoolSize * 2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("health-check-");
        executor.initialize();
        return executor;
    }

    @Bean(name = "redisWriteExecutor")
    public Executor redisWriteExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(redisPoolSize);
        executor.setMaxPoolSize(redisPoolSize * 2);
        executor.setQueueCapacity(1000);
        executor.setThreadNamePrefix("redis-write-");
        executor.initialize();
        return executor;
    }
}
