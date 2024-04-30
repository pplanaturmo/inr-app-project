package com.pplanaturmo.inrappproject.measurement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pplanaturmo.inrappproject.dosePattern.DosePattern;
import com.pplanaturmo.inrappproject.dosePattern.DosePatternRepository;
import com.pplanaturmo.inrappproject.measurement.exceptions.DangerousValueException;
import com.pplanaturmo.inrappproject.user.User;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class MeasurementService {

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private DosePatternRepository dosePatternRepository;

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

    public Measurement findLatestMeasurementByUserId(Long userId) {
        return measurementRepository.findLatestByUserId(userId).get();
    }

    public DosePattern calculatePatternLevel(User user, Double value) {

        final Double TWO_LEVELS_INCREASE_VALUE = 1.3;
        final Double TOO_DANGEROUS_VALUE = 7.0;

        Long patternId = user.getDosePattern().getId();

        if (needToIncreaseLevel(user, value)) {
            patternId = (value <= TWO_LEVELS_INCREASE_VALUE) ? patternId + 2 : patternId +1;
        }

        if (needToDecreaseLevel(user, value)) {
            if (value > TOO_DANGEROUS_VALUE) {
                throw new DangerousValueException("The value is too dangerous. Please consult a doctor.");
            } else {
                patternId --;
            }
        }

        final Long finalPatternId = patternId;
        DosePattern dosePattern = dosePatternRepository.findById(patternId)
                .orElseThrow(() -> new EntityNotFoundException("Dose pattern not found with id: " + finalPatternId));

        return dosePattern;
    }

    private Boolean needToIncreaseLevel(User user, Double value) {
        final int MAX_PATTERN_LEVEL = 56;
        return value > user.getRangeInr().getMaxLevel() && user.getDosePattern().getId() > MAX_PATTERN_LEVEL;
    }

    private Boolean needToDecreaseLevel(User user, Double value) {
        final int MIN_PATTERN_LEVEL = 1;
        return value < user.getRangeInr().getMinLevel() && user.getDosePattern().getId() < MIN_PATTERN_LEVEL;
    }


}
