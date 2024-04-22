package com.pplanaturmo.inrappproject.repository;

import com.pplanaturmo.inrappproject.model.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, Long> {
   
}
