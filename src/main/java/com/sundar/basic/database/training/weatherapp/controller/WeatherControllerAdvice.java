package com.sundar.basic.database.training.weatherapp.controller;


import com.sundar.basic.database.training.weatherapp.model.ExceptionHandleResponse;
import com.sundar.basic.database.training.weatherapp.service.WeatherAppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class WeatherControllerAdvice extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        Map<String, Object> responseMap = new LinkedHashMap<>();
        responseMap.put("timestamp", LocalDate.now());
        responseMap.put("status", status.value());

        List<String> errorMessage = ex.getBindingResult().getFieldErrors().stream().map(msg -> msg.getDefaultMessage()).collect(Collectors.toList());
        responseMap.put("errorMessage", errorMessage);
        return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WeatherAppException.class)
    public final ResponseEntity<Object> handleAllException(WeatherAppException weatherAppException) {
        ExceptionHandleResponse exceptionHandleResponse = new ExceptionHandleResponse();
        //weatherAppException.setCode("ERR001");
        //weatherAppException.setMessage(ex.getMessage());
        //weatherAppException.setStatus("FAILURE");
        exceptionHandleResponse.setStatusCode(weatherAppException.getCode());
        exceptionHandleResponse.setStatusMessage(weatherAppException.getMessage());
        return new ResponseEntity<>(exceptionHandleResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*@ExceptionHandler(WeatherAppException.class)
    protected ResponseEntity<Object> handleBindException(
            BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        weatherAppException.setCode("ERR001");
        weatherAppException.setMessage(ex.getMessage());
        weatherAppException.setStatus("FAILURE");
        return handleExceptionInternal(ex, weatherAppException, headers, status, request);
    }*/

}
