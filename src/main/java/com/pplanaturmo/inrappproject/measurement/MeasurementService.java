package com.pplanaturmo.inrappproject.measurement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MeasurementService {
    @Autowired
    private MeasurementRepository measurementRepository;

     
     public Measurement createMeasurement(Measurement measurement) {
        return measurementRepository.save(measurement);
    }

   
    public List<Measurement> getAllMeasurements() {
        return measurementRepository.findAll();
    }

    public Measurement getMeasurementById(Long id) {
        return measurementRepository.findById(id).orElse(null);
    }

    public Measurement updateMeasurement(Measurement measurement) {
        return measurementRepository.save(measurement);
    }

    public void deleteMeasurement(Long id) {
        measurementRepository.deleteById(id);
    }

    public List<Measurement> getMeasurementsByUserId(Long user_id) {
        return measurementRepository.findByUserId(user_id);
    }

    public Optional<Measurement> findLatestMeasurementByUserId(Long userId) {
        return measurementRepository.findLatestByUserId(userId);
    }
}
