package com.pplanaturmo.inrappproject.dosage;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
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

    public Dosage getDosageByDate(Long userId, Date doseDate) {
        return dosageRepository.findByMeasurement_User_IdAndDoseDate(userId, doseDate)
                .orElse(null);
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

        final Double SKIP_FIRST_DOSE = 5.0;
        final Double NO_DOSAGES = 7.0;
        final Double SKIP_DAY_VALUE = 0.0;
        Double[] dosagesList = measurement.getDosagesValuesList();

        Double value = measurement.getValue();
        if (value > NO_DOSAGES) {
            return;
        } else {

            Date currentDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);

            if (value >= SKIP_FIRST_DOSE) {

                saveDosage(measurement, calendar.getTime(), SKIP_DAY_VALUE);

                for (int i = 1; i < dosagesList.length; i++) {

                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    saveDosage(measurement, calendar.getTime(), dosagesList[i]);

                }
            } else {

                for (int i = 0; i < dosagesList.length; i++) {
                    saveDosage(measurement, calendar.getTime(), SKIP_DAY_VALUE);
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                }

            }
        }
    }

    private void saveDosage(Measurement measurement, Date doseDate, Double doseValue) {
        Dosage dosage = new Dosage();
        dosage.setMeasurement(measurement);
        dosage.setDoseDate(doseDate);
        dosage.setTaken(false);
        dosage.setDoseValue(doseValue);
        createDosage(dosage);
    }
}
