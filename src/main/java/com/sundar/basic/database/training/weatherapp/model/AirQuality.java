package com.sundar.basic.database.training.weatherapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AirQuality{
    public double co;
    public double no2;
    public double o3;
    public double so2;
    public double pm2_5;
    public double pm10;
    @JsonProperty("us-epa-index")
    public int usEpaIndex;
    @JsonProperty("gb-defra-index")
    public int gbDefraIndex;
}
