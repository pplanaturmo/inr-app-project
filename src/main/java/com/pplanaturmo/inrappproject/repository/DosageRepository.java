package com.pplanaturmo.inrappproject.repository;

import com.pplanaturmo.inrappproject.model.Dosage;
import com.pplanaturmo.inrappproject.model.Measurement;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DosageRepository extends JpaRepository<Dosage, Long> {
    
    List<Dosage> findByMeasurement(Measurement measurement);

    List<Dosage> findByDoseDate(Date doseDate);

    List<Dosage> findByDoseDateBetween(Date startDate, Date endDate);
}
