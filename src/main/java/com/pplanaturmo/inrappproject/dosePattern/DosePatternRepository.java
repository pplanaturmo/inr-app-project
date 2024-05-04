package com.pplanaturmo.inrappproject.dosePattern;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pplanaturmo.inrappproject.dosePattern.DosePattern.DrugTypeEnum;

import java.util.List;

@Repository
public interface DosePatternRepository extends JpaRepository<DosePattern, Long> {

    List<DosePattern> findByDrug(DrugTypeEnum drug);

    List<DosePattern> findByLevel(Integer level);

    DosePattern findByDrugAndLevel(DosePattern.DrugTypeEnum drug, Integer level);

}
