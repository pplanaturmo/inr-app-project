package com.pplanaturmo.inrappproject.dosePattern;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DosePatternRepository extends JpaRepository<DosePattern, Long>{

}
