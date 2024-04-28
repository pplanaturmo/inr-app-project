package com.pplanaturmo.inrappproject.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pplanaturmo.inrappproject.data.sintrom4warfarina10.InitialDataS4W10;

import jakarta.annotation.PostConstruct;

@Component
public class InitialDataLoader {

  @Autowired
  private  InitialDataS4W10 initialDataS4W10;

  @PostConstruct
  public void loadInitialData() {

    initialDataS4W10.loadInrRangesS4W10();
    initialDataS4W10.loadDosePatternsS4W10();

  }
}
