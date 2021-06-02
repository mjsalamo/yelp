package com.cresendia.exam.yelp.review.service.config;
/**
 * Globe FinTech Innovations, Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


@EnableAsync
@Configuration
public class RestConfig {

    @Bean
    public ThreadLocal<RestTemplate> threadLocalRestTemplate() {
        return new ThreadLocal<>();
    }

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder();
    }


//    @Bean(name = "taskExecutor")
//    public Executor taskExecutor() {
//        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(5);
//        executor.setMaxPoolSize(5);
//        executor.setQueueCapacity(100);
//        executor.setThreadNamePrefix("LoadsalesInquireServiceThread-");
//        executor.initialize();
//        return executor;
//    }
//
//    @Bean
//    ScheduledExecutorService executorService() {
//        return Executors.newSingleThreadScheduledExecutor();
//    }

}
