package com.sundar.basic.database.training.weatherapp.model;

import com.sundar.basic.database.training.weatherapp.controller.StateNameValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AreaWeather {

    @Id
    @GeneratedValue
    private long id;
    @Size(min=5, message="Zipcode should be 5 character")
    private String zipCode;
    /*@NotEmpty
    private String areaName;
    @StateNameValidation
    private String stateName;
    @Pattern(regexp="^[a-zA-Z0-9]{10}",message="length must be 10")
    private String weather;*/
}
