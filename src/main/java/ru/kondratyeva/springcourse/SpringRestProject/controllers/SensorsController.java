package ru.kondratyeva.springcourse.SpringRestProject.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.kondratyeva.springcourse.SpringRestProject.dto.SensorDTO;
import ru.kondratyeva.springcourse.SpringRestProject.models.Sensor;
import ru.kondratyeva.springcourse.SpringRestProject.services.SensorService;
import ru.kondratyeva.springcourse.SpringRestProject.util.ErrorResponse;
import ru.kondratyeva.springcourse.SpringRestProject.util.SensorNotCreatedException;
import ru.kondratyeva.springcourse.SpringRestProject.util.SensorValidator;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorsController {
    private final SensorService sensorService;
    private final SensorValidator scannerValidator;
    private final ModelMapper modelMapper;
    @Autowired
    public SensorsController(SensorService sensorService, SensorValidator scannerValidator, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.scannerValidator = scannerValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> register(@RequestBody @Valid SensorDTO sensorDTO,
                                               BindingResult bindingResult){
        Sensor sensor = convertToSensor(sensorDTO);
        scannerValidator.validate(sensor, bindingResult);
        if (bindingResult.hasFieldErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            StringBuilder errorMsg = new StringBuilder();
            for(FieldError error: errors){
                errorMsg.append(error.getField()).append("-").append(error.getDefaultMessage());
            }
            throw new SensorNotCreatedException(errorMsg.toString());
        }
        sensorService.save(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorNotCreatedException exc){
        ErrorResponse response = new ErrorResponse(exc.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    public Sensor convertToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }
    public SensorDTO convertToSensorDTO(Sensor sensor){
        return modelMapper.map(sensor, SensorDTO.class);
    }
}
