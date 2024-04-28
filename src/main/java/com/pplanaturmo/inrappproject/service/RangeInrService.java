package com.pplanaturmo.inrappproject.service;

import java.util.List;

import com.pplanaturmo.inrappproject.model.RangeInr;
import com.pplanaturmo.inrappproject.repository.RangeInrRepository;



public class RangeInrService {

    RangeInrRepository rangeInrRepository;

      public List<RangeInr> getAllRangeInrs() {
        return rangeInrRepository.findAll();
    }
}
