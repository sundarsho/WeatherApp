package com.sundar.basic.database.training.weatherapp.controller;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("GoogleAPI")
public class ExternalServiceHealthCheck implements HealthIndicator {

    @Override
    public Health health() {
        try{
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getForEntity("https://googoogo.com", String.class);
            return Health.status("up").build();
        }catch (Exception ex){
            return Health.status("Down").build();
        }

    }

}
