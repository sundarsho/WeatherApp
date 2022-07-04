package com.sundar.basic.database.training.weatherapp.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
public class WeatherAppException extends RuntimeException{


    private String message;
    private String code;
    private String status;

    public WeatherAppException(String message, String code, String status) {
        this.message = message;
        this.code = code;
        this.status = status;
    }
}
