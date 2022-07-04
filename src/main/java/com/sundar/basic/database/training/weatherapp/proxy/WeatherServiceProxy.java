package com.sundar.basic.database.training.weatherapp.proxy;

import com.sundar.basic.database.training.weatherapp.model.AstronomyResponse;
import com.sundar.basic.database.training.weatherapp.model.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URI;

//@FeignClient(name="weather-api", url = "http://api.weatherapi.com")
@FeignClient(name="weather-api", url = "default")
public interface WeatherServiceProxy {

    //http://api.weatherapi.com/v1/current.json?key=9fdf3ebdf82e43ebba1174335221302&q=07001&aqi=yes
    @GetMapping("?key={key}&q={zipCode}&api={api}")
    public WeatherResponse invokeWeatherAPIClient(URI baseUri, @PathVariable String key,
                                                  @PathVariable String zipCode,
                                                  @PathVariable String api);

    @GetMapping("?key={key}&q={zipCode}&api={api}")
    public WeatherResponse invokeWeatherForcastAPIClient(URI baseUri, @PathVariable String key,
                                                  @PathVariable String zipCode,
                                                  @PathVariable String api);

    @GetMapping("?key={key}&q={zipCode}&api={api}")
    public AstronomyResponse invokeWeatherAstronomyAPIClient(URI baseUri, @PathVariable String key,
                                                             @PathVariable String zipCode,
                                                             @PathVariable String api);
}

