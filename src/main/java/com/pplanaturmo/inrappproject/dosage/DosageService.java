package com.pplanaturmo.inrappproject.dosage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pplanaturmo.inrappproject.alerts.Alert;
import com.pplanaturmo.inrappproject.alerts.AlertService;
import com.pplanaturmo.inrappproject.alerts.Alert.LevelEnum;
import com.pplanaturmo.inrappproject.measurement.Measurement;
import com.pplanaturmo.inrappproject.measurement.MeasurementRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DosageService {

    @Autowired
    private DosageRepository dosageRepository;

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private AlertService alertService;

    public Dosage createDosage(Dosage dosage) {
        return dosageRepository.save(dosage);
    }

    public Dosage getDosageById(Long id) {
        return dosageRepository.findById(id).orElse(null);
    }

    public Dosage updateDosage(Dosage dosage) {
        return dosageRepository.save(dosage);
    }

    public void deleteDosage(Long id) {
        dosageRepository.deleteById(id);
    }

    public List<Dosage> getAllDosagesByUser(Long userId) {
        List<Measurement> measurements = measurementRepository.findByUserId(userId);

        List<Dosage> dosages = new ArrayList<>();

        for (Measurement measurement : measurements) {
            List<Dosage> dosagesForMeasurement = dosageRepository.findByMeasurement(measurement);
            dosages.addAll(dosagesForMeasurement);
        }

        return dosages;
    }

    public List<Dosage> getDosageByDate(Long userId, Date doseDate) {
        List<Measurement> measurements = measurementRepository.findByUserId(userId);
        List<Dosage> dosagesForDate = new ArrayList<>();

        for (Measurement measurement : measurements) {

            List<Dosage> dosages = dosageRepository.findByMeasurementAndDoseDate(measurement, doseDate);

            dosagesForDate.addAll(dosages);
        }

        return dosagesForDate;
    }

    public List<Dosage> getDosagesBetweenDates(Long userId, Date startDate, Date endDate) {

        List<Measurement> measurements = measurementRepository.findByUserId(userId);

        List<Dosage> dosages = new ArrayList<>();

        for (Measurement measurement : measurements) {
            List<Dosage> dosagesForMeasurement = dosageRepository.findByMeasurementAndDoseDateBetween(measurement,
                    startDate, endDate);
            dosages.addAll(dosagesForMeasurement);
        }

        return dosages;
    }

    
    public void createDosagesByMeasurement(Measurement measurement) {

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
        Boolean ascendingDose = true;
        
        Double value = measurement.getValue();


        if (value < VERY_LOW_VALUE) {
            numberOfDosages = HIGH_DEVIATION_DAYS;
            
            Alert lowLevel = new Alert();
            lowLevel.setMeasurement(measurement);
            lowLevel.setLevel(LevelEnum.TOO_LOW);
            alertService.createAlert(lowLevel);

        } else if (value < LOW_VALUE) {
            numberOfDosages = MEDIUM_DEVIATION_DAYS;
        } else if (value < IN_RANGE) {
            numberOfDosages = STANDARD_DAYS;
        } else if (value < HIGH_VALUE) {
            numberOfDosages = STANDARD_DAYS;
        } else if (value < VERY_HIGH_VALUE) {
            numberOfDosages = MEDIUM_DEVIATION_DAYS;

            Alert highLevel = new Alert();
            highLevel.setMeasurement(measurement);
            highLevel.setLevel(LevelEnum.TOO_HIGH);
            alertService.createAlert(highLevel);

        } else {
            numberOfDosages = DANGEROUS_DAYS;

            Alert dangerousLevel = new Alert();
            dangerousLevel.setMeasurement(measurement);
            dangerousLevel.setLevel(LevelEnum.DANGEROUS);
            alertService.createAlert(dangerousLevel);
        }

        //TODO   LOGIC FOR CREATING THE DOSAGES AND ITS DATES AND ALL

   List<Dosage> dosages = new ArrayList<>();
        
      
        // Set the initial date as the current day
        Date currentDate = new Date(now);

        // Initialize index for dosage array
        int dosageIndex = 0;

        // Loop to calculate and store dosages
        for (int i = 0; i < numberOfDosages; i++) {
            // Create new dosage
            Dosage dosage = new Dosage();
            dosage.setMeasurement(measurement);
            dosage.setDoseDate(currentDate);
            dosage.setTaken(false); // Assuming initially not taken

            // Set dosage value from array and increment index
            dosage.setDoseValue(dosageValues[dosageIndex]);
            dosageIndex = (dosageIndex + 1) % dosageValues.length; // Wrap around if reached end of array

            // Save dosage
            dosages.add(dosage);

            // Increment date by one day
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            currentDate = calendar.getTime();
        }

        // Save all dosages
        return measurementRepository.saveAll(dosages);
    }

    }
}
