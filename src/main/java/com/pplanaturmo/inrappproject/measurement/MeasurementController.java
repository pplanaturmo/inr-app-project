package com.pplanaturmo.inrappproject.measurement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pplanaturmo.inrappproject.alerts.AlertService;
import com.pplanaturmo.inrappproject.dosage.DosageService;
import com.pplanaturmo.inrappproject.expectedMeasurementDate.ExpectedMeasurementDateService;
import com.pplanaturmo.inrappproject.measurement.dtos.MeasurementRequest;
import com.pplanaturmo.inrappproject.user.User;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RequestMapping("/api/measurement")
public class MeasurementController {

    @Autowired
    private MeasurementService measurementService;

    @Autowired
    private DosageService dosageService;

    @Autowired
    private AlertService alertService;

    @Autowired
    private ExpectedMeasurementDateService expectedMeasurementDateService;

    @PostMapping("/create/{userId}")
    public Measurement createMeasurement(@PathVariable("userId") @Valid @NotNull Long userId,
            @Valid @RequestBody MeasurementRequest measurementRequest) {
        Measurement measurement = measurementService.convertToMeasurement(userId, measurementRequest);
        Measurement newMeasurement = measurementService.createMeasurement(measurement);

        alertService.createAlertIfNeeded(newMeasurement);
        dosageService.createDosagesByMeasurement(newMeasurement);
        Integer daysToNextMeasurement = newMeasurement.getDosagesValuesList().length;
        User measurementUser = measurement.getUser();
        expectedMeasurementDateService.generateExpectedMeasurementDate(daysToNextMeasurement, measurementUser);

        return newMeasurement;
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