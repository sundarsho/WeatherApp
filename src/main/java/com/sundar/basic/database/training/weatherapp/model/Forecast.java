package com.sundar.basic.database.training.weatherapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Forecast{
    public ArrayList<Forecastday> forecastday;
}
