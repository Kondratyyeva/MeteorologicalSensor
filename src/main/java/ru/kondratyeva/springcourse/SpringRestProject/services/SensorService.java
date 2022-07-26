package ru.kondratyeva.springcourse.SpringRestProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kondratyeva.springcourse.SpringRestProject.models.Sensor;
import ru.kondratyeva.springcourse.SpringRestProject.repositories.MeasurementRepository;
import ru.kondratyeva.springcourse.SpringRestProject.repositories.SensorRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;
    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }
    @Transactional
    public void save(Sensor sensor){
        sensorRepository.save(sensor);
    }
    public Sensor findByName(String name){
        Optional<Sensor> sensor = sensorRepository.findByName(name);
        return sensor.orElse(null);
    }
}
