package com.pplanaturmo.inrappproject.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pplanaturmo.inrappproject.model.DosePattern;
import com.pplanaturmo.inrappproject.model.RangeInr;
import com.pplanaturmo.inrappproject.repository.DosePatternRepository;
import com.pplanaturmo.inrappproject.repository.RangeInrRepository;

import jakarta.annotation.PostConstruct;

public class InitialDataLoader {
  @Autowired
  private RangeInrRepository rangeInrRepository;
  private DosePatternRepository dosePatternRepository;

  @PostConstruct
  public void loadInitialData() {

    // Therapeutic range data

    RangeInr regular = new RangeInr(1L, 2.0, 3.0, "Regular interval");
    RangeInr mechanicalValves = new RangeInr(2L, 2.5, 3.5, "Interval for people with mechanical valves");

    rangeInrRepository.saveAll(Arrays.asList(regular, mechanicalValves));

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

    for (DosePattern pattern : patterns) {
      dosePatternRepository.save(pattern);
    }

    // DosePattern pattern01 = new DosePattern(1L,new Double[]{0.0,0.0,0.125});
    // DosePattern pattern02 = new DosePattern(2L,new Double[]{0.0,0.125});
    // DosePattern pattern03 = new DosePattern(3L,new Double[]{0.0,0.125,0.125});
    // DosePattern pattern04 = new DosePattern(4L,new Double[]{0.0, 0.125, 0.125,
    // 0.125});
    // DosePattern pattern05 = new DosePattern(5L,new Double[]{0.125});
    // DosePattern pattern06 = new DosePattern(6L,new
    // Double[]{0.125,0.125,0.125,0.25});
    // DosePattern pattern07 = new DosePattern(7L,new Double[]{0.125,0.125,0.25});
    // DosePattern pattern08 = new DosePattern(8L,new Double[]{0.125,0.25});
    // DosePattern pattern09 = new DosePattern(9L,new Double[]{0.125,0.25,0.25});
    // DosePattern pattern10 = new DosePattern(10L,new
    // Double[]{0.125,0.25,0.25,0.25});
    // DosePattern pattern11 = new DosePattern(11L,new Double[]{0.25});
    // DosePattern pattern12 = new DosePattern(12L,new
    // Double[]{0.25,0.25,0.25,0.5});
    // DosePattern pattern13 = new DosePattern(13L,new Double[]{0.25,0.25,0.5});
    // DosePattern pattern14 = new DosePattern(14L,new Double[]{0.25,0.5});
    // DosePattern pattern15 = new DosePattern(15L,new Double[]{0.25,0.5,0.5});
    // DosePattern pattern16 = new DosePattern(16L,new Double[]{0.25,0.5,0.5,0.5});
    // DosePattern pattern17 = new DosePattern(17L,new Double[]{0.5});
    // DosePattern pattern18 = new DosePattern(18L,new Double[]{0.5,0.5,0.5,0.75});
    // DosePattern pattern19 = new DosePattern(19L,new Double[]{0.5,0.5,0.75});
    // DosePattern pattern20 = new DosePattern(20L,new Double[]{0.5,0.75});
    // DosePattern pattern21 = new DosePattern(21L,new Double[]{0.5,0.75,0.75});
    // DosePattern pattern22 = new DosePattern(22L,new
    // Double[]{0.5,0.75,0.75,0.75});
    // DosePattern pattern23 = new DosePattern(23L,new Double[]{0.75});
    // DosePattern pattern24 = new DosePattern(24L,new
    // Double[]{0.75,0.75,0.75,1.0});
    // DosePattern pattern25 = new DosePattern(25L,new Double[]{0.75,0.75,1.0});
    // DosePattern pattern26 = new DosePattern(26L,new Double[]{0.75,1.0});
    // DosePattern pattern27 = new DosePattern(27L,new Double[]{0.75,1.0,1.0});
    // DosePattern pattern28 = new DosePattern(28L,new Double[]{0.75,1.0,1.0,1.0});
    // DosePattern pattern29 = new DosePattern(29L,new Double[]{1.0});
    // DosePattern pattern30 = new DosePattern(30L,new Double[]{1.0,1.0,1.0,1.25});
    // DosePattern pattern31 = new DosePattern(31L,new Double[]{1.0,1.0,1.25});
    // DosePattern pattern32 = new DosePattern(32L,new Double[]{1.0,1.25});
    // DosePattern pattern33 = new DosePattern(33L,new Double[]{1.0,1.25,1.25});
    // DosePattern pattern34 = new DosePattern(34L,new
    // Double[]{1.0,1.25,1.25,1.25});
    // DosePattern pattern35 = new DosePattern(35L,new Double[]{1.25});
    // DosePattern pattern36 = new DosePattern(36L,new
    // Double[]{1.25,1.25,1.25,1.5});
    // DosePattern pattern37 = new DosePattern(37L,new Double[]{1.25,1.25,1.5});
    // DosePattern pattern38 = new DosePattern(38L,new Double[]{1.25,1.5});
    // DosePattern pattern39 = new DosePattern(39L,new Double[]{1.25,1.5,1.5});
    // DosePattern pattern40 = new DosePattern(40L,new Double[]{1.25,1.5,1.5,1.5});
    // DosePattern pattern41 = new DosePattern(41L,new Double[]{1.5});
    // DosePattern pattern42 = new DosePattern(42L,new Double[]{1.5,1.5,1.5,1.75});
    // DosePattern pattern43 = new DosePattern(43L,new Double[]{1.5,1.5,1.75});
    // DosePattern pattern44 = new DosePattern(44L,new Double[]{1.5,1.75});
    // DosePattern pattern45 = new DosePattern(45L,new Double[]{1.5,1.75,1.75});
    // DosePattern pattern46 = new DosePattern(46L,new
    // Double[]{1.5,1.75,1.75,1.75});
    // DosePattern pattern47 = new DosePattern(47L,new Double[]{1.75});
    // DosePattern pattern48 = new DosePattern(48L,new
    // Double[]{1.75,1.75,1.75,2.0});
    // DosePattern pattern49 = new DosePattern(49L,new Double[]{1.75,1.75,2.0});
    // DosePattern pattern50 = new DosePattern(50L,new Double[]{1.75,2.0});
    // DosePattern pattern51 = new DosePattern(51L,new Double[]{1.75,2.0,2.0});
    // DosePattern pattern52 = new DosePattern(52L,new Double[]{1.75,2.0,2.0,2.0});
    // DosePattern pattern53 = new DosePattern(53L,new Double[]{2.0});
    // DosePattern pattern54 = new DosePattern(54L,new Double[]{2.0,2.0,2.0,2.25});
    // DosePattern pattern55 = new DosePattern(55L,new Double[]{2.0,2.0,2.25});

  }
}

// {"id":"1", "max_level":"3","min_level":"2","description":"regular"}
// {"id":"1", "max_level":"3.5","min_level":"2.5","description":"mechanical
// valves"}