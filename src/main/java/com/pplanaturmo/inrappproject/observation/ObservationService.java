package com.pplanaturmo.inrappproject.observation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pplanaturmo.inrappproject.measurement.Measurement;
import com.pplanaturmo.inrappproject.measurement.MeasurementRepository;
import com.pplanaturmo.inrappproject.observation.Observation.CauseEnum;
import com.pplanaturmo.inrappproject.observation.dtos.ObservationRequest;
import com.pplanaturmo.inrappproject.user.User;
import com.pplanaturmo.inrappproject.user.UserRepository;

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

    public Observation createObservation(Observation observation) {
        return observationRepository.save(observation);
    }

    public Observation convertTObservation(Long userId, ObservationRequest observationRequest) {
        
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
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            if (observationRequest.getDate() == null){
                newObservation.setDate(LocalDate.now());
            }else{
            
            LocalDate date = LocalDate.parse(observationRequest.getDate(), formatter);
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

    public Observation updateObservation(Observation observation) {
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
