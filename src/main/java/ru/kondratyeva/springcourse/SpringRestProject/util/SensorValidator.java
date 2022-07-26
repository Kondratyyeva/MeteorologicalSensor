package ru.kondratyeva.springcourse.SpringRestProject.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kondratyeva.springcourse.SpringRestProject.models.Sensor;
import ru.kondratyeva.springcourse.SpringRestProject.services.SensorService;

@Component
public class SensorValidator implements Validator {
    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    @Autowired
    public SensorValidator(SensorService sensorService, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor= (Sensor) target;
        if(sensorService.findByName(sensor.getName())!=null){
            errors.rejectValue("name","",
                    "Scanner with such name already exist");
        }
    }
}
