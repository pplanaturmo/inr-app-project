package com.pplanaturmo.inrappproject.data;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pplanaturmo.inrappproject.data.initialRoles.InitialRoles;
import com.pplanaturmo.inrappproject.data.sintrom1warfarina1_3_5.InitialDataS1W135;
import com.pplanaturmo.inrappproject.data.sintrom4warfarina10.InitialDataS4W10;
import com.pplanaturmo.inrappproject.rangeInr.RangeInr;
import com.pplanaturmo.inrappproject.rangeInr.RangeInrRepository;

import jakarta.annotation.PostConstruct;

@Component
public class InitialDataLoader {

  @Autowired
  private InitialDataS4W10 initialDataS4W10;

  @Autowired
  private InitialDataS1W135 initialDataS1W135;

  @Autowired
  private RangeInrRepository rangeInrRepository;

  @Autowired
  private InitialRoles initialRoles;

  @PostConstruct
  public void loadInitialData() {

    loadInrRanges();
    initialDataS4W10.loadDosePatternsS4W10();
    initialDataS1W135.loadDosePatternsS1W135();
    initialRoles.loadRoles();

  }

  public void loadInrRanges() {
    RangeInr regular = new RangeInr(1L, 2.0, 3.0, "Regular interval");
    RangeInr mechanicalValves = new RangeInr(2L, 2.5, 3.5, "Interval for people with mechanical valves");

    rangeInrRepository.saveAll(Arrays.asList(regular, mechanicalValves));
  }
}
