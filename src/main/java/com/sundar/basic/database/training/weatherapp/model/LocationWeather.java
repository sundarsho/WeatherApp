package com.sundar.basic.database.training.weatherapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LocationWeather {

    @Id
    public String zipCode;
    public String name;
    public String region;
    public String country;
    public String lastUpdated;
    public double tempF;
    public double windMPH;
    public double feelslikeF;
    public int humidity;
    public String conditionText;

}
