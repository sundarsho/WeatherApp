package com.sundar.basic.database.training.weatherapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Condition{
    public String text;
    public String icon;
    public int code;
}
