package com.sundar.basic.database.training.weatherapp.service;


import com.sundar.basic.database.training.weatherapp.apiclient.WeatherApiClient;
import com.sundar.basic.database.training.weatherapp.model.Location;
import com.sundar.basic.database.training.weatherapp.model.WeatherResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {

    @Mock
    private WeatherApiClient weatherApiClientTest;

    private WeatherService weatherService;
    @BeforeEach
    void setUp(){
        weatherService = new WeatherService();
        ReflectionTestUtils.setField(weatherService, "weatherApiClient", weatherApiClientTest);
    }

@Test
void testInvokeWeatherAPIClientWebClient_WeatherSuccessResp() {
    WeatherResponse weatherResponse = new WeatherResponse();
    Location location = new Location();
    weatherResponse.setLocation(location);
    weatherResponse.getLocation().setCountry("USA");

    when(weatherApiClientTest.invokeWeatherAPIClientWebClient(anyString()))
            .thenReturn(weatherResponse);

    Assertions.assertEquals("USA",weatherService.invokeWeatherAPIClientWebClient("07001").getLocation().getCountry());

}

    @Test
    void testInvokeWeatherAPIClientWebClient_WeatherFailureResp() {
        WeatherResponse weatherResponse = new WeatherResponse();
        Location location = new Location();
        weatherResponse.setLocation(location);
        weatherResponse.getLocation().setCountry("CAN");
        when(weatherApiClientTest.invokeWeatherAPIClientWebClient(anyString()))
                .thenReturn(weatherResponse);
       //when(weatherApiClientTest.invokeWeatherAPIClientWebClient(anyString()))
                //.thenReturn(dothrow new RuntimeException("Country not USA"));
        //doThrow(new RuntimeException("Country not USA")).when(weatherApiClientTest.invokeWeatherAPIClientWebClient(anyString()));
        Assertions.assertThrows(RuntimeException.class,() -> {weatherService.invokeWeatherAPIClientWebClient("07001");});
        //Assertions.assertEquals("USA",weatherService.invokeWeatherAPIClientWebClient("07001").getLocation().getCountry());

    }

}
