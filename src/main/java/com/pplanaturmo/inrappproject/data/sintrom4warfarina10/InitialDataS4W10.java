package com.pplanaturmo.inrappproject.data.sintrom4warfarina10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pplanaturmo.inrappproject.dosePattern.DosePattern;
import com.pplanaturmo.inrappproject.dosePattern.DosePatternRepository;
import com.pplanaturmo.inrappproject.rangeInr.RangeInr;
import com.pplanaturmo.inrappproject.rangeInr.RangeInrRepository;

@Component
public class InitialDataS4W10 {

    @Autowired
    private RangeInrRepository rangeInrRepository;

    @Autowired
    private DosePatternRepository dosePatternRepository;

    // Therapeutic range data

    public void loadInrRangesS4W10() {
        RangeInr regular = new RangeInr(1L, 2.0, 3.0, "Regular interval");
        RangeInr mechanicalValves = new RangeInr(2L, 2.5, 3.5, "Interval for people with mechanical valves");

        rangeInrRepository.saveAll(Arrays.asList(regular, mechanicalValves));
    }

    public void loadDosePatternsS4W10() {

        // PATTERN LEVELS DATA

        List<DosePattern> patterns = new ArrayList<>();

        patterns.add(new DosePattern(1L, new Double[] { 0.0, 0.0, 0.125 }));
        patterns.add(new DosePattern(2L, new Double[] { 0.0, 0.125 }));
        patterns.add(new DosePattern(3L, new Double[] { 0.0, 0.125, 0.125 }));
        patterns.add(new DosePattern(4L, new Double[] { 0.0, 0.125, 0.125, 0.125 }));
        patterns.add(new DosePattern(5L, new Double[] { 0.125 }));
        patterns.add(new DosePattern(6L, new Double[] { 0.125, 0.125, 0.125, 0.25 }));
        patterns.add(new DosePattern(7L, new Double[] { 0.125, 0.125, 0.25 }));
        patterns.add(new DosePattern(8L, new Double[] { 0.125, 0.25 }));
        patterns.add(new DosePattern(9L, new Double[] { 0.125, 0.25, 0.25 }));
        patterns.add(new DosePattern(10L, new Double[] { 0.125, 0.25, 0.25, 0.25 }));
        patterns.add(new DosePattern(11L, new Double[] { 0.25 }));
        patterns.add(new DosePattern(12L, new Double[] { 0.25, 0.25, 0.25, 0.5 }));
        patterns.add(new DosePattern(13L, new Double[] { 0.25, 0.25, 0.5 }));
        patterns.add(new DosePattern(14L, new Double[] { 0.25, 0.5 }));
        patterns.add(new DosePattern(15L, new Double[] { 0.25, 0.5, 0.5 }));
        patterns.add(new DosePattern(16L, new Double[] { 0.25, 0.5, 0.5, 0.5 }));
        patterns.add(new DosePattern(17L, new Double[] { 0.5 }));
        patterns.add(new DosePattern(18L, new Double[] { 0.5, 0.5, 0.5, 0.75 }));
        patterns.add(new DosePattern(19L, new Double[] { 0.5, 0.5, 0.75 }));
        patterns.add(new DosePattern(20L, new Double[] { 0.5, 0.75 }));
        patterns.add(new DosePattern(21L, new Double[] { 0.5, 0.75, 0.75 }));
        patterns.add(new DosePattern(22L, new Double[] { 0.5, 0.75, 0.75, 0.75 }));
        patterns.add(new DosePattern(23L, new Double[] { 0.75 }));
        patterns.add(new DosePattern(24L, new Double[] { 0.75, 0.75, 0.75, 1.0 }));
        patterns.add(new DosePattern(25L, new Double[] { 0.75, 0.75, 1.0 }));
        patterns.add(new DosePattern(26L, new Double[] { 0.75, 1.0 }));
        patterns.add(new DosePattern(27L, new Double[] { 0.75, 1.0, 1.0 }));
        patterns.add(new DosePattern(28L, new Double[] { 0.75, 1.0, 1.0, 1.0 }));
        patterns.add(new DosePattern(29L, new Double[] { 1.0 }));
        patterns.add(new DosePattern(30L, new Double[] { 1.0, 1.0, 1.0, 1.25 }));
        patterns.add(new DosePattern(31L, new Double[] { 1.0, 1.0, 1.25 }));
        patterns.add(new DosePattern(32L, new Double[] { 1.0, 1.25 }));
        patterns.add(new DosePattern(33L, new Double[] { 1.0, 1.25, 1.25 }));
        patterns.add(new DosePattern(34L, new Double[] { 1.0, 1.25, 1.25, 1.25 }));
        patterns.add(new DosePattern(35L, new Double[] { 1.25 }));
        patterns.add(new DosePattern(36L, new Double[] { 1.25, 1.25, 1.25, 1.5 }));
        patterns.add(new DosePattern(37L, new Double[] { 1.25, 1.25, 1.5 }));
        patterns.add(new DosePattern(38L, new Double[] { 1.25, 1.5 }));
        patterns.add(new DosePattern(39L, new Double[] { 1.25, 1.5, 1.5 }));
        patterns.add(new DosePattern(40L, new Double[] { 1.25, 1.5, 1.5, 1.5 }));
        patterns.add(new DosePattern(41L, new Double[] { 1.5 }));
        patterns.add(new DosePattern(42L, new Double[] { 1.5, 1.5, 1.5, 1.75 }));
        patterns.add(new DosePattern(43L, new Double[] { 1.5, 1.5, 1.75 }));
        patterns.add(new DosePattern(44L, new Double[] { 1.5, 1.75 }));
        patterns.add(new DosePattern(45L, new Double[] { 1.5, 1.75, 1.75 }));
        patterns.add(new DosePattern(46L, new Double[] { 1.5, 1.75, 1.75, 1.75 }));
        patterns.add(new DosePattern(47L, new Double[] { 1.75 }));
        patterns.add(new DosePattern(48L, new Double[] { 1.75, 1.75, 1.75, 2.0 }));
        patterns.add(new DosePattern(49L, new Double[] { 1.75, 1.75, 2.0 }));
        patterns.add(new DosePattern(50L, new Double[] { 1.75, 2.0 }));
        patterns.add(new DosePattern(51L, new Double[] { 1.75, 2.0, 2.0 }));
        patterns.add(new DosePattern(52L, new Double[] { 1.75, 2.0, 2.0, 2.0 }));
        patterns.add(new DosePattern(53L, new Double[] { 2.0 }));
        patterns.add(new DosePattern(54L, new Double[] { 2.0, 2.0, 2.0, 2.25 }));
        patterns.add(new DosePattern(55L, new Double[] { 2.0, 2.0, 2.25 }));
        patterns.add(new DosePattern(56L, new Double[] { 2.0, 2.25 }));

        dosePatternRepository.saveAll(patterns);

    }

}
