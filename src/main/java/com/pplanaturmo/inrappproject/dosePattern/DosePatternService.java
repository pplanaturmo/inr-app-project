package com.pplanaturmo.inrappproject.dosePattern;

import java.util.List;

public class DosePatternService {

    DosePatternRepository dosePatternRepository;

    public List<DosePattern> getAllDosePatterns() {

        return dosePatternRepository.findAll();
    }

    public DosePattern findDosePatternByDrugAndLevel(DosePattern.DrugTypeEnum drug, Integer level) {
        return dosePatternRepository.findByDrugAndLevel(drug, level);
    }
}
