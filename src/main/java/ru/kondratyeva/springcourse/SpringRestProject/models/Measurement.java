package ru.kondratyeva.springcourse.SpringRestProject.models;

import jdk.jfr.Timestamp;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "value")
    @NotNull(message = "Field should not be empty")
    @Check(constraints = "value > -101 && value<101")
    private Double value;
    @Column(name = "raining")
    @NotNull(message = "Field should not be empty")
    private Boolean raining;
    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    @NotNull
    private Sensor sensor;
    @Timestamp
    @Column(name = "made_at")
    private LocalDateTime madeAt;

    public Measurement() {
    }

    public Measurement(Double value, Boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }
    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public LocalDateTime getMadeAt() {
        return madeAt;
    }

    public void setMadeAt(LocalDateTime madeAt) {
        this.madeAt = madeAt;
    }
}

