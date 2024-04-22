package com.pplanaturmo.inrappproject.repository;

import com.pplanaturmo.inrappproject.model.Observation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObservationRepository extends JpaRepository<Observation, Long> {

    List<Observation> findByUserId(Long user_id);

    List<Observation> findByMeasurementId(Long measurement_id);
}
