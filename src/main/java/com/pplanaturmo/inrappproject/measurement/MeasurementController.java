package com.pplanaturmo.inrappproject.measurement;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pplanaturmo.inrappproject.dosePattern.DosePattern;
import com.pplanaturmo.inrappproject.measurement.dtos.MeasurementRequest;
import com.pplanaturmo.inrappproject.user.User;
import com.pplanaturmo.inrappproject.user.UserRepository;
import com.pplanaturmo.inrappproject.user.dtos.UserRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/measurement")
public class MeasurementController {

    static final int MAX_PATTERN_LEVEL = 56;
    static final int MIN_PATTERN_LEVEL = 1;

    @Autowired
    private MeasurementService measurementService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create/{userId}")
    public Measurement createMeasurement(@PathVariable("userId") @Valid @NotNull Long userId,
            @Valid @RequestBody MeasurementRequest measurementRequest) {
        Measurement measurement = convertToMeasurement(userId, measurementRequest);
        return measurementService.createMeasurement(measurement);
    }

    private Measurement convertToMeasurement(Long userId, MeasurementRequest measurementRequest) {
        Measurement measurement = new Measurement();
        Date now = new Date();
        User user = userRepository.findById(userId);
        measurement.setUser(user);
        measurement.setDate(now);
        measurement.setValue(measurementRequest.getValue());

        return measurement;
    }

    private Long calculatePatternLevel(User user,Double value){
        DosePattern newPattern = new DosePattern();
        User userCalculate = user;

        if(value > user.getRangeInr().getMaxLevel()){
            if(user.getDosePattern().getId()>MIN_PATTERN_LEVEL){
                newPattern.setId(user.getDosePattern().getId() -1 );
            }
        }

        if(value < user.getRangeInr().getMinLevel()){
            if(user.getDosePattern().getId()<MAX_PATTERN_LEVEL){
                newPattern.setId(user.getDosePattern().getId() -1 );
            }
        }
        
        return 
    }

}
