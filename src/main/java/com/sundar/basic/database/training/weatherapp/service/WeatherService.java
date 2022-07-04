package com.sundar.basic.database.training.weatherapp.service;

import com.sundar.basic.database.training.weatherapp.apiclient.WeatherApiClient;
import com.sundar.basic.database.training.weatherapp.apiclient.WeatherAstronomyAPIClient;
import com.sundar.basic.database.training.weatherapp.model.*;
import com.sundar.basic.database.training.weatherapp.proxy.WeatherServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class WeatherService {

    @Autowired
    private WeatherApiClient weatherApiClient;

    @Autowired
    private WeatherAstronomyAPIClient weatherAstronomyAPIClient;

    @Autowired
    private WeatherServiceProxy weatherServiceProxy;

    @Autowired
    private LocationWeatherDao locationWeatherDao;

    @Value("${weather.service.base.uri}")
    private String baseUri;

    @Value("${weather.service.key}")
    private String apikey;

    public WeatherResponse retrieveWeatherServiceDetail(String zipCode){
        return weatherApiClient.invokeWeatherAPIService(zipCode);
    }

    public LocationWeather invokeWeatherAPIClientFeign(String zipCode){
        URI basePathUri = URI.create(baseUri);

        WeatherResponse weatherResponse = weatherServiceProxy.invokeWeatherAPIClient(basePathUri, apikey, zipCode, "yes");
        LocationWeather locationWeather = locationWeatherDao.saveLocationWeather(weatherResponse, zipCode);

        return locationWeather;
    }

    public WeatherResponse invokeWeatherForcastAPIClientFeign(String zipCode){
        //URI basePathUri = URI.create(baseUri);

        WeatherResponse weatherResponse = weatherAstronomyAPIClient.invokeWeatherAPIClientFeign( zipCode);
        //LocationWeather locationWeather = locationWeatherDao.saveLocationWeather(weatherResponse, zipCode);

        return weatherResponse;
    }

    public AstronomyResponse invokeWeatherAstronomyAPIClientFeign(String zipCode){
        //URI basePathUri = URI.create(baseUri);

        AstronomyResponse astronomyResponse = weatherAstronomyAPIClient.invokeWeatherAstronomyAPIClientFeign( zipCode);
        //LocationWeather locationWeather = locationWeatherDao.saveLocationWeather(weatherResponse, zipCode);

        return astronomyResponse;
    }

    public AstronomyResponse invokeBothWeatherAstronomyAPIClientFeignAsync(String zipCode) throws ExecutionException, InterruptedException {
        URI basePathUri = URI.create(baseUri);
        CompletableFuture<WeatherResponse> completableFutureWeatherResponse = weatherApiClient.invokeWeatherAPIClientFeignAsync( zipCode);
        CompletableFuture<AstronomyResponse> completableFutureAstronomyResponse = weatherAstronomyAPIClient.invokeWeatherAstronomyAPIClientFeignAsync( zipCode);
        //LocationWeather locationWeather = locationWeatherDao.saveLocationWeather(weatherResponse, zipCode);
        CompletableFuture.allOf(completableFutureWeatherResponse, completableFutureAstronomyResponse).join();
        WeatherResponse weatherResponse = completableFutureWeatherResponse.get();
        AstronomyResponse astronomyResponse = completableFutureAstronomyResponse.get();
        return astronomyResponse;
    }


    public WeatherResponse invokeWeatherAPIClientWebClient(String zipCode) {
        WeatherResponse weatherResponse = weatherApiClient.invokeWeatherAPIClientWebClient( zipCode);
        Location location = weatherResponse.getLocation();
        if(location.getCountry().equalsIgnoreCase("USA")){
            System.out.println("Display");
            return weatherResponse;
        }
        throw new RuntimeException("Country not USA");
    }
}
