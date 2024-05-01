package com.pplanaturmo.inrappproject.measurement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pplanaturmo.inrappproject.alerts.Alert;
import com.pplanaturmo.inrappproject.alerts.Alert.LevelEnum;
import com.pplanaturmo.inrappproject.dosePattern.DosePattern;
import com.pplanaturmo.inrappproject.dosePattern.DosePatternRepository;
import com.pplanaturmo.inrappproject.measurement.exceptions.DangerousValueException;
import com.pplanaturmo.inrappproject.user.User;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder.In;
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
                patternId--;
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


    public Double[] calculateDosagesPattern(Double value,DosePattern pattern){

        final int STANDARD_DAYS = 7;
        final int MEDIUM_DEVIATION_DAYS = 5;
        final int HIGH_DEVIATION_DAYS = 4;
        final int DANGEROUS_DAYS = 0;
        final Double VERY_LOW_VALUE = 1.3;
        final Double LOW_VALUE = 1.8;
        final Double IN_RANGE = 3.2;
        final Double HIGH_VALUE = 4.9;
        final Double VERY_HIGH_VALUE = 7.0;

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

        if(value<LOW_VALUE){
            Double[] increasingDosePattern = reversePattern(pattern.getPatternValue());
            doseValuesList = calculateValuesForChangingLevel(numberOfDosages,increasingDosePattern);
        }else if(value <= IN_RANGE){
            doseValuesList =  calculateValuesForInRange(numberOfDosages,pattern.getPatternValue());
        }else{
            doseValuesList = calculateValuesForChangingLevel(numberOfDosages,pattern.getPatternValue());
        }



        return doseValuesList;


    } 

    private Double[] calculateValuesForChangingLevel(Integer numberOfDosages,Double[] pattern){
        Double[] values = new Double[numberOfDosages];
        for (int i = 0; i < numberOfDosages; i++) {
            
            int index = i % pattern.length;
            values[i] = pattern[index];
        }

        return values;
    }

    private Double[] calculateValuesForInRange(Integer numberOfDosages,Double[] pattern){
        Double[] values = new Double[numberOfDosages];


        return values;
    }

    private Double[] reversePattern(Double[] pattern){
        Double[] reversedPattern = new Double[pattern.length];
        int length = pattern.length;
        for (int i = 0; i < length; i++) {
            reversedPattern[i] = pattern[length - i - 1];
         }
        return reversedPattern;
    }


}
