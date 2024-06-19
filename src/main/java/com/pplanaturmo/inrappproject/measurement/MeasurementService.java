package com.pplanaturmo.inrappproject.measurement;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pplanaturmo.inrappproject.dosePattern.DosePattern;
import com.pplanaturmo.inrappproject.dosePattern.DosePatternRepository;
import com.pplanaturmo.inrappproject.dosePattern.DosePattern.DrugTypeEnum;
import com.pplanaturmo.inrappproject.measurement.dtos.MeasurementRequest;
import com.pplanaturmo.inrappproject.user.User;
import com.pplanaturmo.inrappproject.user.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MeasurementService {

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private DosePatternRepository dosePatternRepository;

    @Autowired
    private UserRepository userRepository;

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
//        return measurementRepository.findLatestCreatedByUserId(userId).get();
        return measurementRepository.findLatestByUserId(userId);
    }

    public Measurement convertToMeasurement(Long userId, MeasurementRequest measurementRequest) {


        Measurement measurement = new Measurement();
        LocalDate now = LocalDate.now();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        DosePattern pattern = calculatePatternLevel(user, measurementRequest.getValue());

//        Optional<Measurement> previousMeasurement = measurementRepository.findLatestCreatedByUserId(userId);
        Optional<Measurement> previousMeasurement = measurementRepository.findFirstByUserIdOrderByCreatedAtDesc(userId);

        Double[] dosagesList = calculateDosagesList(measurementRequest.getValue(), pattern, previousMeasurement);

        measurement.setUser(user);
        measurement.setDate(now);
        measurement.setValue(measurementRequest.getValue());
        measurement.setRecommendedPattern(pattern);
        measurement.setDosagesValuesList(dosagesList);

        user.setDosePattern(pattern);
        User savedUser = userRepository.save(user);
        return measurement;
    }

    public DosePattern calculatePatternLevel(User user, Double value) {

        final Double TWO_LEVELS_INCREASE_VALUE = 1.3;
        final Double TWO_LEVELS_DECREASE_VALUE = 5.0;
        final Double TOO_DANGEROUS_VALUE = 7.0;

        Integer patternLevel = user.getDosePattern().getLevel();


        Boolean increaseLevelValue = needToIncreaseLevel(user, value);
        Boolean decreaseLevelValue = needToDecreaseLevel(user, value);
        if (increaseLevelValue) {
            patternLevel = (value <= TWO_LEVELS_INCREASE_VALUE) ? patternLevel + 2 : patternLevel + 1;
        }

        if (decreaseLevelValue) {

            if (value > TOO_DANGEROUS_VALUE) {
                patternLevel = null;
                //throw new DangerousValueException("The value is too dangerous. Please consult a doctor.");
            } else if (value >= TWO_LEVELS_DECREASE_VALUE) {
                patternLevel = patternLevel - 2;
            } else {
                patternLevel--;

            }
        }


        DrugTypeEnum drug = user.getDosePattern().getDrug();
        DosePattern newDosePattern = dosePatternRepository.findByDrugAndLevel(drug, patternLevel);

        return newDosePattern;
    }

    private Boolean needToIncreaseLevel(User user, Double value) {
        final int MAX_PATTERN_LEVEL = 56;
        final Double MIN_LEVEL_INR23 = 1.9;

        return value < MIN_LEVEL_INR23 && user.getDosePattern().getId() < MAX_PATTERN_LEVEL;
    }

    private Boolean needToDecreaseLevel(User user, Double value) {
        final int MIN_PATTERN_LEVEL = 1;
        final Double MAX_LEVEL_INR23 = 3.2;

        return value > MAX_LEVEL_INR23 && user.getDosePattern().getId() > MIN_PATTERN_LEVEL;
    }

    public Double[] calculateDosagesList(Double value, DosePattern pattern, Optional<Measurement> previousMeasurement) {

        final int STANDARD_DAYS = 7;
        final int MEDIUM_DEVIATION_DAYS = 5;
        final int HIGH_DEVIATION_DAYS = 4;
        final int DANGEROUS_DAYS = 0;
        final Double VERY_LOW_VALUE = 1.3;
        final Double LOW_VALUE = 1.8;
        final Double IN_RANGE = 3.2;
        final Double HIGH_VALUE = 4.9;
        final Double VERY_HIGH_VALUE = 7.0;

        Double[] previousDosesValue = previousMeasurement
                .map(Measurement::getDosagesValuesList)
                .orElse(new Double[0]);

        Integer numberOfDosages;


        if (value < VERY_LOW_VALUE) {
            numberOfDosages = HIGH_DEVIATION_DAYS;
        } else if (value < LOW_VALUE) {
            numberOfDosages = MEDIUM_DEVIATION_DAYS;
        } else if (value < IN_RANGE) {
            numberOfDosages = STANDARD_DAYS;
        } else if (value < HIGH_VALUE) {
            numberOfDosages = STANDARD_DAYS;
        } else if (value < VERY_HIGH_VALUE) {
            numberOfDosages = MEDIUM_DEVIATION_DAYS;

        } else {
            numberOfDosages = DANGEROUS_DAYS;
        }

        Double[] doseValuesList = new Double[numberOfDosages];
        if (pattern != null) {
            Double[] invertedPattern = reversePattern(pattern.getPatternValue());
            Double[] regularPattern = pattern.getPatternValue();

            if (value < LOW_VALUE) {
                doseValuesList = calculateValuesForChangingLevel(numberOfDosages, invertedPattern);
            } else if (value <= IN_RANGE) {

                doseValuesList = continuePreviousPattern(numberOfDosages, regularPattern, previousDosesValue);
            } else {

                doseValuesList = calculateValuesForChangingLevel(numberOfDosages, regularPattern);
            }
        }

        return doseValuesList;
    }

    private Double[] calculateValuesForChangingLevel(Integer numberOfDosages, Double[] pattern) {

        Double[] values = new Double[numberOfDosages];
        for (int i = 0; i < numberOfDosages; i++) {

            int index = i % pattern.length;
            values[i] = pattern[index];
        }
        return values;
    }

    private Double[] continuePreviousPattern(Integer numberOfDosages, Double[] pattern, Double[] previousValues) {
        Double[] values = new Double[numberOfDosages];

        if (previousValues.length == 0) {
            for (int i = 0; i < values.length; i++) {
                int index = (i) % pattern.length;
                values[i] = pattern[index];
            }
        } else {

            Double lastValue = previousValues[previousValues.length - 1];
            int startIndex = 0;

            for (int i = 0; i < pattern.length; i++) {
                if (pattern[i].equals(lastValue)) {
                    startIndex = (i + 1) % pattern.length;
                    break;
                }
            }

            for (int i = 0; i < values.length; i++) {
                int index = (startIndex + i) % pattern.length;
                values[i] = pattern[index];
            }

        }

        return values;
    }

    private Double[] reversePattern(Double[] pattern) {
        Double[] reversedPattern = new Double[pattern.length];
        int length = pattern.length;
        for (int i = 0; i < length; i++) {
            reversedPattern[i] = pattern[length - i - 1];
        }
        return reversedPattern;
    }
}
