package com.sundar.basic.database.training.weatherapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Day{
    @JsonProperty(value = "maxtemp_c")
    public double maxTempCelsius;
    public double maxtemp_f;
    public double mintemp_c;
    public double mintemp_f;
    public double avgtemp_c;
    public double avgtemp_f;
    public double maxwind_mph;
    public double maxwind_kph;
    public double totalprecip_mm;
    public double totalprecip_in;
    public double avgvis_km;
    public double avgvis_miles;
    public double avghumidity;
    public int daily_will_it_rain;
    public int daily_chance_of_rain;
    public int daily_will_it_snow;
    public int daily_chance_of_snow;
    public Condition condition;
    public double uv;
}
