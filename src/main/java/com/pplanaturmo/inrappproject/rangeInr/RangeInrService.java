package com.pplanaturmo.inrappproject.rangeInr;

import java.util.List;



public class RangeInrService {

    RangeInrRepository rangeInrRepository;

      public List<RangeInr> getAllRangeInrs() {
        return rangeInrRepository.findAll();
    }
}
