package com.pplanaturmo.inrappproject.observation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObservationRepository extends JpaRepository<Observation, Long> {

    List<Observation> findByUserId(Long user_id);

    List<Observation> findByMeasurementId(Long measurement_id);
}
