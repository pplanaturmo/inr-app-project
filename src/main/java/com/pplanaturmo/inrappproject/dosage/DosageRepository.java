package com.pplanaturmo.inrappproject.dosage;

import com.pplanaturmo.inrappproject.measurement.Measurement;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DosageRepository extends JpaRepository<Dosage, Long> {
    
    List<Dosage> findByMeasurement(Measurement measurement);

    List<Dosage> findByDoseDate(LocalDate doseDate);

    List<Dosage> findByMeasurementAndDoseDateBetween(Measurement measurement,LocalDate startDate, LocalDate endDate);

    List<Dosage> findByMeasurementAndDoseDate(Measurement measurement,LocalDate doseDate);

    Optional<Dosage> findByMeasurement_User_IdAndDoseDate(Long userId, LocalDate doseDate);

}
