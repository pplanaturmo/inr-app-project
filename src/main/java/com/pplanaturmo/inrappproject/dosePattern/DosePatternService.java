package com.pplanaturmo.inrappproject.dosePattern;

import java.util.List;

public class DosePatternService {

    DosePatternRepository dosePatternRepository;

    public List<DosePattern> getAllDosePatterns(){

        return dosePatternRepository.findAll();
    }
}
