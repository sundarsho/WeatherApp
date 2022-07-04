package com.sundar.basic.database.training.weatherapp.controller;

import com.sundar.basic.database.training.weatherapp.model.*;
import com.sundar.basic.database.training.weatherapp.repository.AreaWeatherRepository;
import com.sundar.basic.database.training.weatherapp.service.WeatherAppException;
import com.sundar.basic.database.training.weatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/weather/v1")
//Combination of @Controller - @ResponseEntity
public class WeatherController {

    @Autowired
    private AreaWeatherRepository areaWeatherRepository;

        @Autowired
        private WeatherService weatherService;

        @Autowired
        private LocationWeatherDao locationWeatherDao;


    /*private WeatherService weatherService;

    @Autowired
    private WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;
    }*/

    @PostMapping("/daily-weather")
    public ResponseEntity weather(@RequestBody @Valid AreaWeather areaWeather){
        try {
            //areaWeatherRepository.s                                                                                         ave(areaWeather);

            //WeatherResponse weatherResponse = weatherService.retrieveWeatherServiceDetail(areaWeather.getZipCode());
            LocationWeather locationWeather = weatherService.invokeWeatherAPIClientFeign(areaWeather.getZipCode());
            return new ResponseEntity(locationWeather, HttpStatus.CREATED);
        }catch(WeatherAppException exception){
            return new ResponseEntity(exception, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/daily-weather/{zipCode}")
    public LocationWeather retrieveWeatherbyZipCode(@PathVariable String zipCode){
        LocationWeather locationWeather = locationWeatherDao.retrieveWetherDetailbyZipCode(zipCode);
        if(locationWeather==null){
            throw new WeatherAppException("Location Not Found", "ERR001", "NOT_FOUND");
        }
        //return new ResponseEntity(locationWeather, HttpSta);
        return locationWeather;
    }

    @GetMapping("/weatherlocation")
    public List<LocationWeather> retrieveWeatherDetails(){
        List<LocationWeather> locationWeatherList = locationWeatherDao.retrieveWetherDetail();
        if(locationWeatherList.isEmpty()){
            throw new WeatherAppException("Locations Not Found", "ERR002", "NOT_FOUND");
        }
        //return new ResponseEntity(locationWeatherList, HttpStatus.valueOf("SUCCESS"));
        return locationWeatherList;
    }

    @GetMapping("/forecast/{zipCode}")
    public WeatherResponse retrieveWeatherForecastbyZipCode(@PathVariable String zipCode){
        WeatherResponse weatherResponse = weatherService.invokeWeatherForcastAPIClientFeign(zipCode);
       /* if(weatherResponse==null){
            throw new WeatherAppException("Location Not Found", "ERR001", "NOT_FOUND");
        }*/
        //return new ResponseEntity(locationWeather, HttpSta);
        return weatherResponse;
    }

    @GetMapping("/astronomy/{zipCode}")
    public AstronomyResponse retrieveWeatherAstronomybyZipCode(@PathVariable String zipCode){
        AstronomyResponse astronomyResponse = weatherService.invokeWeatherAstronomyAPIClientFeign(zipCode);
       /* if(weatherResponse==null){
            throw new WeatherAppException("Location Not Found", "ERR001", "NOT_FOUND");
        }*/
        //return new ResponseEntity(locationWeather, HttpSta);
        return astronomyResponse;
    }

    @PostMapping("/weather-astronomy")
    public AstronomyResponse retrieveWeatherAstronomybyZipCode(@RequestBody @Valid AreaWeather areaWeather) throws ExecutionException, InterruptedException {
        AstronomyResponse astronomyResponse = weatherService.invokeBothWeatherAstronomyAPIClientFeignAsync(areaWeather.getZipCode());

        return astronomyResponse;
    }



    @GetMapping("/weatherwebclient/{zipCode}")
    public WeatherResponse retrieveWeatherWebClientbyZipCode(@PathVariable String zipCode){
        WeatherResponse weatherResponse = weatherService.invokeWeatherAPIClientWebClient(zipCode);
       /* if(weatherResponse==null){
            throw new WeatherAppException("Location Not Found", "ERR001", "NOT_FOUND");
        }*/
        //return new ResponseEntity(locationWeather, HttpSta);
        return weatherResponse;
    }


    /*@PostMapping("/weatherUpdate")
    public ResponseEntity weatherUpdate(@RequestBody @Valid AreaWeather areaWeather){
        areaWeatherRepository.save(areaWeather);
        return new ResponseEntity("success", HttpStatus.CREATED);
    }*/


}
