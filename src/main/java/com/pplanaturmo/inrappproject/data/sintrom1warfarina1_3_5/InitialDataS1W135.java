package com.pplanaturmo.inrappproject.data.sintrom1warfarina1_3_5;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pplanaturmo.inrappproject.dosePattern.DosePattern;
import com.pplanaturmo.inrappproject.dosePattern.DosePatternRepository;
import com.pplanaturmo.inrappproject.dosePattern.DosePattern.DrugTypeEnum;

@Component
public class InitialDataS1W135 {

    @Autowired
    private DosePatternRepository dosePatternRepository;

    // // Therapeutic range data

    // public void loadInrRangesS4W10() {
    // RangeInr regular = new RangeInr(1L, 2.0, 3.0, "Regular interval");
    // RangeInr mechanicalValves = new RangeInr(2L, 2.5, 3.5, "Interval for people
    // with mechanical valves");

    // rangeInrRepository.saveAll(Arrays.asList(regular, mechanicalValves));
    // }

    public void loadDosePatternsS1W135() {

        // PATTERN LEVELS DATA

        List<DosePattern> patterns = new ArrayList<>();

        DrugTypeEnum s4w135 = DrugTypeEnum.SINTROM_1_WARFARINA_1_3_5;

        patterns.add(new DosePattern(1, new Double[] { 0.0, 0.0, 0.5 }, s4w135));
        patterns.add(new DosePattern(2, new Double[] { 0.0, 0.5 }, s4w135));
        patterns.add(new DosePattern(3, new Double[] { 0.0, 0.5, 0.5 }, s4w135));
        patterns.add(new DosePattern(4, new Double[] { 0.0, 0.5, 0.5, 0.5 }, s4w135));
        patterns.add(new DosePattern(5, new Double[] { 0.5 }, s4w135));
        patterns.add(new DosePattern(6, new Double[] { 0.5, 0.5, 0.5, 1.0 }, s4w135));
        patterns.add(new DosePattern(7, new Double[] { 0.5, 0.5, 1.0 }, s4w135));
        patterns.add(new DosePattern(8, new Double[] { 0.5, 1.0 }, s4w135));
        patterns.add(new DosePattern(9, new Double[] { 0.5, 1.0, 1.0 }, s4w135));
        patterns.add(new DosePattern(10, new Double[] { 0.5, 1.0, 1.0, 1.0 }, s4w135));
        patterns.add(new DosePattern(11, new Double[] { 1.0 }, s4w135));
        patterns.add(new DosePattern(12, new Double[] { 1.0, 1.0, 1.0, 2.0 }, s4w135));
        patterns.add(new DosePattern(13, new Double[] { 1.0, 1.0, 2.0 }, s4w135));
        patterns.add(new DosePattern(14, new Double[] { 1.0, 2.0 }, s4w135));
        patterns.add(new DosePattern(15, new Double[] { 1.0, 2.0, 2.0 }, s4w135));
        patterns.add(new DosePattern(16, new Double[] { 1.0, 2.0, 2.0, 2.0 }, s4w135));
        patterns.add(new DosePattern(17, new Double[] { 2.0 }, s4w135));
        patterns.add(new DosePattern(18, new Double[] { 2.0, 2.0, 2.0, 3.0 }, s4w135));

        dosePatternRepository.saveAll(patterns);

    }
}