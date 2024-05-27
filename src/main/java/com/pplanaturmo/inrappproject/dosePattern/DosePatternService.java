package com.pplanaturmo.inrappproject.dosePattern;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;


@Service
@Transactional
@Validated
public class DosePatternService {

    @Autowired
    DosePatternRepository dosePatternRepository;

    public List<DosePattern> getAllDosePatterns() {

        return dosePatternRepository.findAll();
    }

    public DosePattern findDosePatternByDrugAndLevel(DosePattern.DrugTypeEnum drug, Integer level) {
        return dosePatternRepository.findByDrugAndLevel(drug, level);
    }

}
