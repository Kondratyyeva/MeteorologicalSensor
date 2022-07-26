package ru.kondratyeva.springcourse.SpringRestProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kondratyeva.springcourse.SpringRestProject.models.Measurement;
import ru.kondratyeva.springcourse.SpringRestProject.models.Sensor;

import java.util.Optional;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    public Optional<Measurement> findBySensor(Sensor sensor);
}
