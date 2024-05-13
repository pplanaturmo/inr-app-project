package com.pplanaturmo.inrappproject.observation;

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

import com.pplanaturmo.inrappproject.aggregatedDtos.DatesBetweenDto;
import com.pplanaturmo.inrappproject.observation.dtos.ObservationRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RequestMapping("/api/observation")
public class ObservationController {

    @Autowired
    private ObservationService observationService;

    @PostMapping("/create/{userId}")
    public Observation createObservation(@PathVariable("userId") @Valid @NotNull Long userId,
            @Valid @RequestBody ObservationRequest observationRequest) {

        Observation newObservation = observationService.convertToObservation(userId, observationRequest);

        return observationService.createObservation(newObservation);
    }

    @GetMapping("/")
    public List<Observation> getAllObservations() {
        return observationService.getAllObservations();
    }

    @GetMapping("/user/{userId}")
    public List<Observation> getObservationsByUserId(@PathVariable("userId") @Valid @NotNull Long userId) {
        return observationService.getObservationsByUserId(userId);
    }

    @GetMapping("/measurement/{measurementId}")
    public List<Observation> getObservationsByMeasurementId(
            @PathVariable("measurementId") @Valid @NotNull Long measurementId) {
        return observationService.getObservationsByMeasurementId(measurementId);
    }

    @GetMapping("/between-dates")
    public List<Observation> getObservationsBetweenDates(@Valid @RequestBody DatesBetweenDto datesBetweenDto) {

        return getObservationsBetweenDates(datesBetweenDto);
    }

    @PutMapping("/{observationId}")
    public Observation updateMeasurement(@PathVariable("observationId") @Valid @NotNull Long observationId,
            @Valid @RequestBody ObservationRequest observationRequest) {

        return observationService.updateObservation(observationId, observationRequest);
    }

    @DeleteMapping("/{observationId}")
    public void deleteUser(@Valid @PathVariable("observationId") @NotNull Long observationId) {
        observationService.deleteObservation(observationId);
    }

}
