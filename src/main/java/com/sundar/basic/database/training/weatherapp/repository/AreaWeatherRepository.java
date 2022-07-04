package com.sundar.basic.database.training.weatherapp.repository;

import com.sundar.basic.database.training.weatherapp.model.AreaWeather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaWeatherRepository extends JpaRepository<AreaWeather, Long> {
}
