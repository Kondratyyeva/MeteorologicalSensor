package ru.kondratyeva.springcourse.SpringRestProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kondratyeva.springcourse.SpringRestProject.models.Sensor;

import java.util.Optional;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    public Optional<Sensor> findByName(String name);
}
