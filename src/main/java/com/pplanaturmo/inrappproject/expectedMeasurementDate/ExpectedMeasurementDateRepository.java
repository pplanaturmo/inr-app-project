package com.pplanaturmo.inrappproject.expectedMeasurementDate;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpectedMeasurementDateRepository extends JpaRepository<ExpectedMeasurementDate, Long> {

    List<ExpectedMeasurementDate> findByUserId(Long userId);

    List<ExpectedMeasurementDate> findByFulfilled(Boolean fulfilled);

    @Query("SELECT e FROM ExpectedMeasurementDate e WHERE e.expectedDate <> e.fullfilled_date")
    List<ExpectedMeasurementDate> findByMismatchedDates();

    Optional<ExpectedMeasurementDate> findTopByUserIdOrderByExpectedDateDesc(Long userId);

}
