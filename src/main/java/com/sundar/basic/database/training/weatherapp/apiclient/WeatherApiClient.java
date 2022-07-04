package com.sundar.basic.database.training.weatherapp.apiclient;

import com.sundar.basic.database.training.weatherapp.controller.WeatherAppConfiguration;
import com.sundar.basic.database.training.weatherapp.model.WeatherResponse;
import com.sundar.basic.database.training.weatherapp.proxy.WeatherServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

@Component
public class WeatherApiClient {

    @Autowired
    private WeatherAppConfiguration weatherAppConfiguration;

    @Value("${weather.service.base.uri}")
    private String baseUri;

    @Value("${weather.service.key}")
    private String apikey;

    @Autowired
    private WeatherServiceProxy weatherServiceProxy;

    public WeatherResponse invokeWeatherAPIService(String zipCode){
        RestTemplate restTemplate = weatherAppConfiguration.restTemplateObj();
       String finalUri = UriComponentsBuilder.fromHttpUrl(baseUri).queryParam("key", apikey)
                .queryParam("q",zipCode).queryParam("api", "yes").toUriString();

        ResponseEntity<WeatherResponse> responseEntity = restTemplate.getForEntity(finalUri, WeatherResponse.class);
        return responseEntity.getBody();
    }

    public WeatherResponse invokeWeatherAPIClientFeign(String zipCode){

        URI basePathUri = URI.create(baseUri);

        WeatherResponse weatherResponse = weatherServiceProxy.invokeWeatherAPIClient(basePathUri, apikey, zipCode, "yes");
        //LocationWeather locationWeather = locationWeatherDao.saveLocationWeather(weatherResponse, zipCode);

        return weatherResponse;
    }

    @Async(value = "asyncWeatherExecutor")
    public CompletableFuture<WeatherResponse> invokeWeatherAPIClientFeignAsync(String zipCode){

        URI basePathUri = URI.create(baseUri);
        System.out.println("invokeWeatherAPIClientFeignAsync:::::"+Thread.currentThread().getName());
        WeatherResponse weatherResponse = weatherServiceProxy.invokeWeatherAPIClient(basePathUri, apikey, zipCode, "yes");
        //LocationWeather locationWeather = locationWeatherDao.saveLocationWeather(weatherResponse, zipCode);
        return CompletableFuture.completedFuture(weatherResponse);
        //return weatherResponse;
    }

    public WeatherResponse invokeWeatherAPIClientWebClient(String zipCode) {
        //URI basePathUri = URI.create(baseUri);
        String finalUri = UriComponentsBuilder.fromHttpUrl(baseUri).queryParam("key", apikey)
                .queryParam("q",zipCode).queryParam("api", "yes").toUriString();
        WebClient.Builder builder = WebClient.builder();
        return builder.build().get().uri(finalUri).retrieve().bodyToMono(WeatherResponse.class).block();
    }
}
