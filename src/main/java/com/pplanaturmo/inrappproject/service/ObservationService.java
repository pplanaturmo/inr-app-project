package com.pplanaturmo.inrappproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pplanaturmo.inrappproject.model.Observation;
import com.pplanaturmo.inrappproject.repository.ObservationRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ObservationService {
    @Autowired
    private ObservationRepository observationRepository;

     
     public Observation createObservation(Observation observation) {
        return observationRepository.save(observation);
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
