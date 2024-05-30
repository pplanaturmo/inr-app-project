package com.pplanaturmo.inrappproject.dosage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pplanaturmo.inrappproject.aggregatedDtos.DatesBetweenDto;
import com.pplanaturmo.inrappproject.dosage.dtos.DosageRequest;
import com.pplanaturmo.inrappproject.dosage.dtos.DosageResponse;
import com.pplanaturmo.inrappproject.measurement.Measurement;
import com.pplanaturmo.inrappproject.measurement.MeasurementRepository;
import com.pplanaturmo.inrappproject.utilities.DateManagement;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DosageService {

    @Autowired
    private DosageRepository dosageRepository;

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private DateManagement dateManagement;

    public Dosage createDosage(Dosage dosage) {
        return dosageRepository.save(dosage);
    }

    public Dosage getDosageById(Long id) {
        return dosageRepository.findById(id).orElse(null);
    }

    public Dosage updateDosage(Dosage dosage) {
        return dosageRepository.save(dosage);
    }

    public Dosage convertToDosageToUpdate(DosageRequest dosageRequest) {
        Dosage dosageToUpdate = dosageRepository.findById(dosageRequest.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + dosageRequest.getId()));
        dosageToUpdate.setTaken(dosageRequest.getTaken());

        return dosageToUpdate;
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

    public Dosage getDosageByDate(Long userId, LocalDate doseDate) {

        return dosageRepository.findByMeasurement_User_IdAndDoseDate(userId, doseDate)
                .orElse(null);
    }

    /*
     * public List<Dosage> getDosagesBetweenDates(DatesBetweenDto datesBetweenDto) {
     * 
     * Long userId = datesBetweenDto.getUserId();
     * LocalDate startDate =
     * dateManagement.convertToLocalDate(datesBetweenDto.getStartDate());
     * LocalDate endDate =
     * dateManagement.convertToLocalDate(datesBetweenDto.getFinishDate());
     * 
     * List<Measurement> measurements = measurementRepository.findByUserId(userId);
     * 
     * List<Dosage> dosages = new ArrayList<>();
     * 
     * for (Measurement measurement : measurements) {
     * List<Dosage> dosagesForMeasurement =
     * dosageRepository.findByMeasurementAndDoseDateBetween(measurement,
     * startDate, endDate);
     * dosages.addAll(dosagesForMeasurement);
     * }
     * 
     * return dosages;
     * }
     */

    public void createDosagesByMeasurement(Measurement measurement) {

        final Double SKIP_FIRST_DOSE = 5.0;
        final Double NO_DOSAGES = 7.0;

        Double[] dosagesList = measurement.getDosagesValuesList();
        Double value = measurement.getValue();

        LocalDate dosageDate = LocalDate.now();

        // If the patient has already taken a dose today and the pattern has to start the next day

        if (dosagesStartNextDay(measurement, dosageDate)) {
            dosageDate = dosageDate.plusDays(1);
        }

        //remove previous doses, now that we have a fresh measurement
        DatesBetweenDto toDeleteDto = new DatesBetweenDto();
        toDeleteDto.setUserId(measurement.getUser().getId());
        toDeleteDto.setStartDate(dosageDate.toString());
        toDeleteDto.setFinishDate(dosageDate.plusDays(7).toString());

        List<DosageResponse> oldDosages = getDosagesBetweenDates(toDeleteDto);
        deleteDosages(oldDosages);

        if (value > NO_DOSAGES) {

            return;
        } else {

            if (value >= SKIP_FIRST_DOSE) {
                createDosagesHighValue(measurement, dosageDate, dosagesList);
            } else {
                createDosages(measurement, dosageDate, dosagesList);
            }
        }
    }

   private Boolean dosagesStartNextDay(Measurement measurement, LocalDate dosageDate) {
       LocalDate measurementDate = measurement.getDate();
       boolean doseExists = getDosageByDate(measurement.getUser().getId(), measurementDate) != null;

       return doseExists ? getDosageByDate(measurement.getUser().getId(), measurementDate).getTaken() : false;
   }
    public List<DosageResponse> getDosagesBetweenDates(DatesBetweenDto datesBetweenDto) {
        Long userId = datesBetweenDto.getUserId();
        LocalDate startDate = dateManagement.convertToLocalDate(datesBetweenDto.getStartDate());
        LocalDate endDate = dateManagement.convertToLocalDate(datesBetweenDto.getFinishDate());

        List<Dosage> dosages = dosageRepository.findByMeasurement_User_IdAndDoseDateBetween(userId, startDate, endDate);

        return dosages.stream()
                .map(dosage -> new DosageResponse(dosage.getId(), dosage.getDoseValue(),
                        java.sql.Date.valueOf(dosage.getDoseDate()), dosage.getTaken()))
                .collect(Collectors.toList());
    }

    public void deleteDosages(List<DosageResponse> dosagesToDelete) {
        for (DosageResponse dosageResponse : dosagesToDelete) {
            Long dosageId = dosageResponse.getId();
            Dosage dosage = dosageRepository.findById(dosageId).orElse(null);
            if (dosage != null) {
                dosageRepository.delete(dosage);
            }
        }
    }

    private void createDosagesHighValue(Measurement measurement, LocalDate dosageDate, Double[] dosagesList) {
        final Double SKIP_DAY_VALUE = 0.0;
        Long userId = measurement.getId();

        if (dosageDateExists(userId, dosageDate)) {
            Dosage existingDosage = getDosageByDate(userId, dosageDate);
            existingDosage.setMeasurement(measurement);
            existingDosage.setDoseValue(SKIP_DAY_VALUE);
            updateDosage(existingDosage);
        } else {
            saveDosage(measurement, dosageDate, SKIP_DAY_VALUE);
        }

        for (int i = 1; i < dosagesList.length; i++) {
            dosageDate = dosageDate.plusDays(1);
            if (dosageDateExists(userId, dosageDate)) {
                Dosage existingDosage = getDosageByDate(userId, dosageDate);
                existingDosage.setMeasurement(measurement);
                existingDosage.setDoseValue(measurement.getValue());
                updateDosage(existingDosage);
            } else {
                saveDosage(measurement, dosageDate, dosagesList[i]);
            }

        }
    }

    private boolean dosageDateExists(Long userId, LocalDate dosageDate) {

        return getDosageByDate(userId, dosageDate) != null;
    }

    private void createDosages(Measurement measurement, LocalDate dosageDate, Double[] dosagesList) {
        Long userId = measurement.getId();
        for (int i = 0; i < dosagesList.length; i++) {

            if (dosageDateExists(userId, dosageDate)) {
                Dosage existingDosage = getDosageByDate(userId, dosageDate);
                existingDosage.setMeasurement(measurement);
                existingDosage.setDoseValue(measurement.getValue());
                updateDosage(existingDosage);
                dosageDate = dosageDate.plusDays(1);

            } else {
                saveDosage(measurement, dosageDate, dosagesList[i]);
                dosageDate = dosageDate.plusDays(1);

            }
        }
    }

    private void saveDosage(Measurement measurement, LocalDate doseDate, Double doseValue) {
        Dosage dosage = new Dosage();
        dosage.setMeasurement(measurement);
        dosage.setDoseDate(doseDate);
        dosage.setTaken(false);
        dosage.setDoseValue(doseValue);
        createDosage(dosage);
    }
}
