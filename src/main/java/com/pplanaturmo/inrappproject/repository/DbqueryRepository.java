package com.pplanaturmo.inrappproject.repository;

import com.pplanaturmo.inrappproject.model.Dbquery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DbqueryRepository extends JpaRepository<Dbquery, Long> {
  
}
