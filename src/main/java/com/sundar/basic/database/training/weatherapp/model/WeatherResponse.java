package com.sundar.basic.database.training.weatherapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeatherResponse{
    public Location location;
    public Current current;
    public Forecast forecast;
}

