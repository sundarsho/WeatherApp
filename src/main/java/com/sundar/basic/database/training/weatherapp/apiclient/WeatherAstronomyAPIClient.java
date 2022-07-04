package com.sundar.basic.database.training.weatherapp.apiclient;


import com.sundar.basic.database.training.weatherapp.model.AstronomyResponse;
import com.sundar.basic.database.training.weatherapp.model.WeatherResponse;
import com.sundar.basic.database.training.weatherapp.proxy.WeatherServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

@Component
public class WeatherAstronomyAPIClient {

    @Value("${weather.service.key}")
    private String apikey;

    @Value("${weather.service.base.forcasturi}")
    private String forcastBaseURI;

    @Value("${weather.service.base.astronomyuri}")
    private String astronomyuri;

    @Autowired
    private WeatherApiClient weatherApiClient;

    @Autowired
    private WeatherServiceProxy weatherServiceProxy;

    public WeatherResponse invokeWeatherAPIClientFeign(String zipCode){
        URI basePathUri = URI.create(forcastBaseURI);

        WeatherResponse weatherResponse = weatherServiceProxy.invokeWeatherAPIClient(basePathUri, apikey, zipCode, "yes");
        //LocationWeather locationWeather = locationWeatherDao.saveLocationWeather(weatherResponse, zipCode);

        return weatherResponse;
    }


    public AstronomyResponse invokeWeatherAstronomyAPIClientFeign(String zipCode) {
        URI basePathUri = URI.create(astronomyuri);
        AstronomyResponse astronomyResponse = weatherServiceProxy.invokeWeatherAstronomyAPIClient(basePathUri, apikey, zipCode, "yes");
        return astronomyResponse;
    }

    @Async(value = "asyncWeatherExecutor")
    public CompletableFuture<AstronomyResponse> invokeWeatherAstronomyAPIClientFeignAsync(String zipCode) {
        System.out.println("invokeWeatherAstronomyAPIClientFeignAsync:::::"+Thread.currentThread().getName());
        URI basePathUri = URI.create(astronomyuri);
        AstronomyResponse astronomyResponse = weatherServiceProxy.invokeWeatherAstronomyAPIClient(basePathUri, apikey, zipCode, "yes");
        return CompletableFuture.completedFuture(astronomyResponse);
        //return astronomyResponse;
    }
}
