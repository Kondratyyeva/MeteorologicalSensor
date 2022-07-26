package ru.kondratyeva.springcourse.SpringRestProject.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.kondratyeva.springcourse.SpringRestProject.dto.MeasurementDTO;
import ru.kondratyeva.springcourse.SpringRestProject.models.Measurement;
import ru.kondratyeva.springcourse.SpringRestProject.services.MeasurementService;
import ru.kondratyeva.springcourse.SpringRestProject.util.ErrorResponse;
import ru.kondratyeva.springcourse.SpringRestProject.util.MeasurementNotCreatedException;
import ru.kondratyeva.springcourse.SpringRestProject.util.MeasurementValidator;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;
    private final ModelMapper modelMapper;
    @Autowired
    public MeasurementsController(MeasurementService measurementService, MeasurementValidator measurementValidator, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<MeasurementDTO> findAll(){
        return measurementService.findAll().stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO,
                                          BindingResult bindingResult){
        Measurement measurement = convertToMeasurement(measurementDTO);
        measurementValidator.validate(measurement, bindingResult);
        if(bindingResult.hasFieldErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            StringBuilder errorMsg = new StringBuilder();
            for(FieldError error: errors){
                errorMsg.append(error.getField()).append("-").append(error.getDefaultMessage());
            }
            throw new MeasurementNotCreatedException(errorMsg.toString());
        }
        measurementService.save(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping("/rainyDaysCount")
    public Integer countRainyDays(){
        List<Measurement> measurements = measurementService.findAll();
        Integer result=0;
        for(Measurement measurement: measurements){
            if(measurement.getRaining())
                result++;
        }
        return result;
    }
    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(MeasurementNotCreatedException exc){
        ErrorResponse response = new ErrorResponse(exc.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    public Measurement convertToMeasurement(MeasurementDTO measurementDTO){
        return modelMapper.map(measurementDTO, Measurement.class);
    }
    public MeasurementDTO convertToMeasurementDTO(Measurement measurement){
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
