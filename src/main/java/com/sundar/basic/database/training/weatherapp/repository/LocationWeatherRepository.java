package com.sundar.basic.database.training.weatherapp.repository;

import com.sundar.basic.database.training.weatherapp.model.LocationWeather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationWeatherRepository extends JpaRepository<LocationWeather,String> {

}
