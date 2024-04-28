package com.pplanaturmo.inrappproject.dosage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
