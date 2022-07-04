package com.sundar.basic.database.training.weatherapp.controller;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class StateNameValidator implements ConstraintValidator<StateNameValidation, String> {
    @Override
    public boolean isValid(String stateName, ConstraintValidatorContext cxt) {
        List<String> list = Arrays.asList(new String[]{"New Jersey","Chicago","Vermont"});
        return list.contains(stateName);
    }
}
