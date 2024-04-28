package com.pplanaturmo.inrappproject.service;

import java.util.List;

import com.pplanaturmo.inrappproject.model.DosePattern;
import com.pplanaturmo.inrappproject.repository.DosePatternRepository;

public class DosePatternService {

    DosePatternRepository dosePatternRepository;

    public List<DosePattern> getAllDosePatterns(){

        return dosePatternRepository.findAll();
    }
}
