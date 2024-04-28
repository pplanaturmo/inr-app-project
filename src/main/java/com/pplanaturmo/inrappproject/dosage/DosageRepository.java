package com.pplanaturmo.inrappproject.dosage;

import com.pplanaturmo.inrappproject.measurement.Measurement;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DosageRepository extends JpaRepository<Dosage, Long> {
    
    List<Dosage> findByMeasurement(Measurement measurement);

    List<Dosage> findByDoseDate(Date doseDate);

    List<Dosage> findByMeasurementAndDoseDateBetween(Measurement measurement,Date startDate, Date endDate);

    List<Dosage> findByMeasurementAndDoseDate(Measurement measurement,Date doseDate);
}
