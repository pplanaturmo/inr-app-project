package com.pplanaturmo.inrappproject.observation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pplanaturmo.inrappproject.aggregatedDtos.DatesBetweenDto;
import com.pplanaturmo.inrappproject.measurement.Measurement;
import com.pplanaturmo.inrappproject.measurement.MeasurementRepository;
import com.pplanaturmo.inrappproject.observation.Observation.CauseEnum;
import com.pplanaturmo.inrappproject.observation.dtos.ObservationRequest;
import com.pplanaturmo.inrappproject.user.User;
import com.pplanaturmo.inrappproject.user.UserRepository;
import com.pplanaturmo.inrappproject.utilities.DateManagement;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ObservationService {

    @Autowired
    private ObservationRepository observationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private DateManagement dateManagement;

    public Observation createObservation(Observation observation) {
        return observationRepository.save(observation);
    }

    public Observation convertToObservation(Long userId, ObservationRequest observationRequest) {

        Observation newObservation = new Observation();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        newObservation.setUser(user);

        if (observationRequest.getMeasurementId() == null) {
            newObservation.setMeasurement(null);
        } else {
            Measurement measurement = measurementRepository.findById(observationRequest.getMeasurementId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Measurement not found with id: " + observationRequest.getMeasurementId()));
            newObservation.setMeasurement(measurement);
        }

        if (observationRequest.getDate() == null) {
            newObservation.setDate(LocalDate.now());
        } else {

            LocalDate date = dateManagement.convertToLocalDate(observationRequest.getDate());
            newObservation.setDate(date);
        }

        CauseEnum cause = CauseEnum.valueOf(observationRequest.getCause().toUpperCase());
        newObservation.setCause(cause);
        newObservation.setDescription(observationRequest.getDescription());

        return newObservation;
    }

    public List<Observation> getAllObservations() {
        return observationRepository.findAll();
    }

    public Observation getObservationById(Long id) {
        return observationRepository.findById(id).orElse(null);
    }

    public List<Observation> getObservationsBetweenDates(DatesBetweenDto datesBetweenDto) {
        Long userId = datesBetweenDto.getUserId();
        LocalDate startDate = dateManagement.convertToLocalDate(datesBetweenDto.getStartDate());
        LocalDate endDate = dateManagement.convertToLocalDate(datesBetweenDto.getFinishDate());
        return observationRepository.findByUserIdAndDateBetween(userId, startDate, endDate);
    }

    // public List<Observation> getObservationsBetweenDates(Long userId, LocalDate
    // startDate, LocalDate endDate) {

    // List<Observation> observations = observationRepository.findByUserId(userId);

    // List<Observation> observationsBetweenDates =
    // observationRepository.findByDateBetween(
    // startDate, endDate);
    // return observationsBetweenDates;
    // }

    public Observation updateObservation(Long observationId, ObservationRequest observationRequest) {

        Observation observation = observationRepository.findById(observationId)
                .orElseThrow(() -> new EntityNotFoundException("Observation not found with ID: " + observationId));
        Long userId = observationRequest.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        Long measurementId = observationRequest.getMeasurementId();
        Measurement measurement = measurementRepository.findById(measurementId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        observation.setUser(user);
        observation.setMeasurement(measurement);
        observation.setDate(dateManagement.convertToLocalDate(observationRequest.getDate()));
        observation.setCause(Observation.CauseEnum.valueOf(observationRequest.getCause().toUpperCase()));
        observation.setDescription(observationRequest.getDescription());

        return observationRepository.save(observation);
    }

    public void deleteObservation(Long id) {
        observationRepository.deleteById(id);
    }

    public List<Observation> getObservationsByUserId(Long user_id) {
        return observationRepository.findByUserId(user_id);
    }

    public List<Observation> getObservationsByMeasurementId(Long measurement_id) {
        return observationRepository.findByMeasurementId(measurement_id);
    }

}
