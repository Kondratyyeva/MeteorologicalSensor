package ru.kondratyeva.springcourse.SpringRestProject.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kondratyeva.springcourse.SpringRestProject.models.Measurement;
import ru.kondratyeva.springcourse.SpringRestProject.models.Sensor;
import ru.kondratyeva.springcourse.SpringRestProject.services.MeasurementService;
import ru.kondratyeva.springcourse.SpringRestProject.services.SensorService;
import ru.kondratyeva.springcourse.SpringRestProject.services.MeasurementService;
import ru.kondratyeva.springcourse.SpringRestProject.services.SensorService;

@Component
public class MeasurementValidator implements Validator {
    private final MeasurementService measurementService;
    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    @Autowired
    public MeasurementValidator(MeasurementService measurementService, SensorService sensorService, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;
        Sensor sensor = measurement.getSensor();
        if(sensor==null){
            return;
        }
        if(sensorService.findByName(sensor.getName())==null) {
            errors.rejectValue("sensor","",
                    "Sensor with this name is not registered in the system");
        }

    }
}
