package com.sundar.basic.database.training.weatherapp.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WeatherAppConfiguration {

    @Bean
    public RestTemplate restTemplateObj(){
        return new RestTemplate();
    }

    @Bean(name="asyncWeatherExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor1 = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor1.setCorePoolSize(2);
        threadPoolTaskExecutor1.setMaxPoolSize(10);
        threadPoolTaskExecutor1.setThreadNamePrefix("AsyncThreadName-");
        threadPoolTaskExecutor1.setQueueCapacity(50);
        threadPoolTaskExecutor1.initialize();
        return threadPoolTaskExecutor1;
    }
}
