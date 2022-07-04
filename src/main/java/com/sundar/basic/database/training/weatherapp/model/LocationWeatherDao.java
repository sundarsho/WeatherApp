package com.sundar.basic.database.training.weatherapp.model;

import com.sundar.basic.database.training.weatherapp.repository.LocationWeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LocationWeatherDao {

    @Autowired
    private LocationWeatherRepository locationWeatherRepository;

    public LocationWeather saveLocationWeather(WeatherResponse weatherResponse,
                                                String zipCode) {
        LocationWeather locationWeather = new LocationWeather();
        locationWeather.setCountry(weatherResponse.getLocation().getCountry());
        locationWeather.setName(weatherResponse.getLocation().getName());
        locationWeather.setRegion(weatherResponse.getLocation().getRegion());
        locationWeather.setZipCode(zipCode);
        locationWeather.setHumidity(weatherResponse.getCurrent().getHumidity());
        locationWeather.setFeelslikeF(weatherResponse.getCurrent().getFeelslike_f());
        locationWeather.setTempF(weatherResponse.getCurrent().getTemp_f());
        locationWeather.setWindMPH(weatherResponse.getCurrent().getWind_mph());
        locationWeather.setLastUpdated(weatherResponse.getCurrent().getLast_updated());
        locationWeather.setConditionText(weatherResponse.getCurrent().getCondition().getText());
        LocationWeather locationWeatherSave = locationWeatherRepository.save(locationWeather);
        return locationWeatherSave;
    }

    public LocationWeather retrieveWetherDetailbyZipCode(String zipCode) {
        Optional<LocationWeather> optionalLocationWeather = locationWeatherRepository.findById(zipCode);
        if(optionalLocationWeather.isPresent()){
            return optionalLocationWeather.get();
        }else{
            return null;
        }

    }

    public List<LocationWeather> retrieveWetherDetail() {
        List<LocationWeather> locationWeatherList = locationWeatherRepository.findAll();
        return locationWeatherList;
    }
}
