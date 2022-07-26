package ru.kondratyeva.springcourse.SpringRestProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kondratyeva.springcourse.SpringRestProject.models.Measurement;
import ru.kondratyeva.springcourse.SpringRestProject.models.Sensor;
import ru.kondratyeva.springcourse.SpringRestProject.repositories.MeasurementRepository;
import ru.kondratyeva.springcourse.SpringRestProject.repositories.SensorRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository,
                              SensorRepository sensorRepository) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository=sensorRepository;
    }

    public List<Measurement> findAll(){
        return measurementRepository.findAll();
    }
    @Transactional
    public void save(Measurement measurement){
        Sensor sensor = sensorRepository.findByName(measurement.getSensor().getName()).get();
        measurement.setSensor(sensor);
        sensor.getMeasurements().add(measurement);
        measurement.setMadeAt(LocalDateTime.now());
        measurementRepository.save(measurement);
    }
    public Measurement findBySensor(Sensor sensor){
        Optional<Measurement> measurement = measurementRepository.findBySensor(sensor);
        return measurement.orElse(null);
    }
}

