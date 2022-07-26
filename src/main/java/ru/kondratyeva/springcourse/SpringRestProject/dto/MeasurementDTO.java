package ru.kondratyeva.springcourse.SpringRestProject.dto;

import org.hibernate.annotations.Check;
import ru.kondratyeva.springcourse.SpringRestProject.models.Sensor;

import javax.validation.constraints.NotNull;

public class MeasurementDTO {

    @NotNull(message = "Field should not be empty")
    @Check(constraints = "value > -101 && value<101")
    private Double value;

    @NotNull(message = "Field should not be empty")
    private Boolean raining;
    @NotNull
    private SensorDTO sensor;

    public MeasurementDTO() {
    }

    public MeasurementDTO(Double value, Boolean raining, SensorDTO sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }
}
