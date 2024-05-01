package com.pplanaturmo.inrappproject.measurement;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pplanaturmo.inrappproject.dosage.DosageService;
import com.pplanaturmo.inrappproject.dosePattern.DosePattern;
import com.pplanaturmo.inrappproject.measurement.dtos.MeasurementRequest;
import com.pplanaturmo.inrappproject.user.User;
import com.pplanaturmo.inrappproject.user.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/measurement")
public class MeasurementController {


    @Autowired
    private MeasurementService measurementService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DosageService dosageService;
   

    @PostMapping("/create/{userId}")
    public Measurement createMeasurement(@PathVariable("userId") @Valid @NotNull Long userId,
            @Valid @RequestBody MeasurementRequest measurementRequest) {
        Measurement measurement = convertToMeasurement(userId, measurementRequest);
        Measurement newMeasurement = measurementService.createMeasurement(measurement);
        
        dosageService.createDosagesByMeasurement(newMeasurement);

        return newMeasurement;
    }

    private Measurement convertToMeasurement(Long userId, MeasurementRequest measurementRequest) {
        Measurement measurement = new Measurement();
        Date now = new Date();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        DosePattern pattern = measurementService.calculatePatternLevel(user, measurementRequest.getValue());
        Double[] dosagesPattern = measurementService.calculateDosagesPattern(measurementRequest.getValue(),pattern);

        measurement.setUser(user);
        measurement.setDate(now);
        measurement.setValue(measurementRequest.getValue());
        measurement.setRecommendedPattern(pattern);
        measurement.setDosagesPattern(dosagesPattern);
        return measurement;
    }


    @GetMapping("/")
    public List<Measurement> getAllMeasurements() {
        return measurementService.getAllMeasurements();
    }

    @GetMapping("/{measurementId}")
    public Measurement getMeasurementById(@PathVariable("measurementId") @Valid @NotNull Long measurementId) {
        return measurementService.getMeasurementById(measurementId);
    }

    @GetMapping("/user/{userId}")
    public List<Measurement> getMeasurementsByUserId(@PathVariable("userId") @Valid @NotNull Long userId) {
        return measurementService.getMeasurementsByUserId(userId);
    }

    @GetMapping("/last/{userId}")
    public Measurement findLatestMeasurementByUserId(@PathVariable("userId") @Valid @NotNull Long userId) {
        return measurementService.findLatestMeasurementByUserId(userId);
    }

    @PutMapping("/{measurementId}")
    public Measurement updateMeasurement(@PathVariable("measurementId") @Valid @NotNull Long measurementId,
            @Valid @RequestBody MeasurementRequest measurementRequest) {

        Measurement measurementToUpdate = getMeasurementById(measurementId);
        measurementToUpdate.setValue(measurementRequest.getValue());

        return measurementService.updateMeasurement(measurementToUpdate);
    }

    @DeleteMapping("/{measurementId}")
    public void deleteMeasurement(@PathVariable("measurementId") @Valid @NotNull Long measurementId) {
        measurementService.deleteMeasurement(measurementId);
    }


}