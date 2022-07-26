package ru.kondratyeva.springcourse.SpringRestProject.dto;

import ru.kondratyeva.springcourse.SpringRestProject.models.Measurement;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SensorDTO {
    @NotNull(message = "Field should not be empty")
    @Size(min=3, max = 30, message = "Sensor name contains more than 3 and less than 30 characters")
    private String name;

    public SensorDTO() {
    }

    public SensorDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
