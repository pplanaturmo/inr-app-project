package com.pplanaturmo.inrappproject.repository;

import com.pplanaturmo.inrappproject.model.ExpectedMeasurementDate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpectedMeasurementDateRepository extends JpaRepository<ExpectedMeasurementDate, Long> {

    List<ExpectedMeasurementDate> findByUserId(Long userId);

    List<ExpectedMeasurementDate> findByFulfilled(Boolean fulfilled);

    // @Query("SELECT e FROM ExpectedMeasurementDate e WHERE e.expectedDate <>
    // e.fullfilledDate")
    // List<ExpectedMeasurementDate> findByMismatchedDates();
    // @Query("SELECT e FROM ExpectedMeasurementDate e WHERE
    // DATEDIFF(e.expectedDate, e.fullfilledDate) != 0")
    // List<ExpectedMeasurementDate> findByMismatchedDates();

    @Query("SELECT e FROM ExpectedMeasurementDate e WHERE e.expectedDate <> e.fullfilled_date")
    List<ExpectedMeasurementDate> findByMismatchedDates();

}
