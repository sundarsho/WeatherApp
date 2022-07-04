package com.sundar.basic.database.training.weatherapp;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableFeignClients
@EnableAsync
@EnableBatchProcessing
public class WeatherAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(WeatherAppApplication.class, args);
	}

}
