package com.pplanaturmo.inrappproject.data.sintrom4warfarina10;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pplanaturmo.inrappproject.dosePattern.DosePattern;
import com.pplanaturmo.inrappproject.dosePattern.DosePatternRepository;
import com.pplanaturmo.inrappproject.dosePattern.DosePattern.DrugTypeEnum;

@Component
public class InitialDataS4W10 {

    @Autowired
    private DosePatternRepository dosePatternRepository;

    // Therapeutic range data

    public void loadDosePatternsS4W10() {

        // PATTERN LEVELS DATA

        List<DosePattern> patterns = new ArrayList<>();

        DrugTypeEnum s4w10 = DrugTypeEnum.SINTROM_4_WARFARINA_10;

        patterns.add(new DosePattern(1, new Double[] { 0.0, 0.0, 0.125 }, s4w10));
        patterns.add(new DosePattern(2, new Double[] { 0.0, 0.125 }, s4w10));
        patterns.add(new DosePattern(3, new Double[] { 0.0, 0.125, 0.125 }, s4w10));
        patterns.add(new DosePattern(4, new Double[] { 0.0, 0.125, 0.125, 0.125 }, s4w10));
        patterns.add(new DosePattern(5, new Double[] { 0.125 }, s4w10));
        patterns.add(new DosePattern(6, new Double[] { 0.125, 0.125, 0.125, 0.25 }, s4w10));
        patterns.add(new DosePattern(7, new Double[] { 0.125, 0.125, 0.25 }, s4w10));
        patterns.add(new DosePattern(8, new Double[] { 0.125, 0.25 }, s4w10));
        patterns.add(new DosePattern(9, new Double[] { 0.125, 0.25, 0.25 }, s4w10));
        patterns.add(new DosePattern(10, new Double[] { 0.125, 0.25, 0.25, 0.25 }, s4w10));
        patterns.add(new DosePattern(11, new Double[] { 0.25 }, s4w10));
        patterns.add(new DosePattern(12, new Double[] { 0.25, 0.25, 0.25, 0.5 }, s4w10));
        patterns.add(new DosePattern(13, new Double[] { 0.25, 0.25, 0.5 }, s4w10));
        patterns.add(new DosePattern(14, new Double[] { 0.25, 0.5 }, s4w10));
        patterns.add(new DosePattern(15, new Double[] { 0.25, 0.5, 0.5 }, s4w10));
        patterns.add(new DosePattern(16, new Double[] { 0.25, 0.5, 0.5, 0.5 }, s4w10));
        patterns.add(new DosePattern(17, new Double[] { 0.5 }, s4w10));
        patterns.add(new DosePattern(18, new Double[] { 0.5, 0.5, 0.5, 0.75 }, s4w10));
        patterns.add(new DosePattern(19, new Double[] { 0.5, 0.5, 0.75 }, s4w10));
        patterns.add(new DosePattern(20, new Double[] { 0.5, 0.75 }, s4w10));
        patterns.add(new DosePattern(21, new Double[] { 0.5, 0.75, 0.75 }, s4w10));
        patterns.add(new DosePattern(22, new Double[] { 0.5, 0.75, 0.75, 0.75 }, s4w10));
        patterns.add(new DosePattern(23, new Double[] { 0.75 }, s4w10));
        patterns.add(new DosePattern(24, new Double[] { 0.75, 0.75, 0.75, 1.0 }, s4w10));
        patterns.add(new DosePattern(25, new Double[] { 0.75, 0.75, 1.0 }, s4w10));
        patterns.add(new DosePattern(26, new Double[] { 0.75, 1.0 }, s4w10));
        patterns.add(new DosePattern(27, new Double[] { 0.75, 1.0, 1.0 }, s4w10));
        patterns.add(new DosePattern(28, new Double[] { 0.75, 1.0, 1.0, 1.0 }, s4w10));
        patterns.add(new DosePattern(29, new Double[] { 1.0 }, s4w10));
        patterns.add(new DosePattern(30, new Double[] { 1.0, 1.0, 1.0, 1.25 }, s4w10));
        patterns.add(new DosePattern(31, new Double[] { 1.0, 1.0, 1.25 }, s4w10));
        patterns.add(new DosePattern(32, new Double[] { 1.0, 1.25 }, s4w10));
        patterns.add(new DosePattern(33, new Double[] { 1.0, 1.25, 1.25 }, s4w10));
        patterns.add(new DosePattern(34, new Double[] { 1.0, 1.25, 1.25, 1.25 }, s4w10));
        patterns.add(new DosePattern(35, new Double[] { 1.25 }, s4w10));
        patterns.add(new DosePattern(36, new Double[] { 1.25, 1.25, 1.25, 1.5 }, s4w10));
        patterns.add(new DosePattern(37, new Double[] { 1.25, 1.25, 1.5 }, s4w10));
        patterns.add(new DosePattern(38, new Double[] { 1.25, 1.5 }, s4w10));
        patterns.add(new DosePattern(39, new Double[] { 1.25, 1.5, 1.5 }, s4w10));
        patterns.add(new DosePattern(40, new Double[] { 1.25, 1.5, 1.5, 1.5 }, s4w10));
        patterns.add(new DosePattern(41, new Double[] { 1.5 }, s4w10));
        patterns.add(new DosePattern(42, new Double[] { 1.5, 1.5, 1.5, 1.75 }, s4w10));
        patterns.add(new DosePattern(43, new Double[] { 1.5, 1.5, 1.75 }, s4w10));
        patterns.add(new DosePattern(44, new Double[] { 1.5, 1.75 }, s4w10));
        patterns.add(new DosePattern(45, new Double[] { 1.5, 1.75, 1.75 }, s4w10));
        patterns.add(new DosePattern(46, new Double[] { 1.5, 1.75, 1.75, 1.75 }, s4w10));
        patterns.add(new DosePattern(47, new Double[] { 1.75 }, s4w10));
        patterns.add(new DosePattern(48, new Double[] { 1.75, 1.75, 1.75, 2.0 }, s4w10));
        patterns.add(new DosePattern(49, new Double[] { 1.75, 1.75, 2.0 }, s4w10));
        patterns.add(new DosePattern(50, new Double[] { 1.75, 2.0 }, s4w10));
        patterns.add(new DosePattern(51, new Double[] { 1.75, 2.0, 2.0 }, s4w10));
        patterns.add(new DosePattern(52, new Double[] { 1.75, 2.0, 2.0, 2.0 }, s4w10));
        patterns.add(new DosePattern(53, new Double[] { 2.0 }, s4w10));
        patterns.add(new DosePattern(54, new Double[] { 2.0, 2.0, 2.0, 2.25 }, s4w10));
        patterns.add(new DosePattern(55, new Double[] { 2.0, 2.0, 2.25 }, s4w10));
        patterns.add(new DosePattern(56, new Double[] { 2.0, 2.25 }, s4w10));

        dosePatternRepository.saveAll(patterns);

    }

}
