package ru.kondratyeva.springcourse.SpringRestProject.util;

public class MeasurementNotCreatedException extends RuntimeException{
    public MeasurementNotCreatedException(String message) {
        super(message);
    }
}
