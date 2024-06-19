package com.pplanaturmo.inrappproject.measurement;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    List<Measurement> findByUserId(Long user_id);

    
    @Query("SELECT m FROM Measurement m WHERE m.user.id = :userId ORDER BY m.date DESC")
    Optional<Measurement> findLatestByUserId(Long userId);

//    @Query("SELECT m FROM Measurement m WHERE m.user.id = :userId ORDER BY m.createdAt DESC")
//    Optional<Measurement> findLatestCreatedByUserId(@Param("userId") Long userId);

    Optional<Measurement> findFirstByUserIdOrderByCreatedAtDesc(Long userId);


}
