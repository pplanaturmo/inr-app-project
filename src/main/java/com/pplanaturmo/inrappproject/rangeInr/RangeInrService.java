package com.pplanaturmo.inrappproject.rangeInr;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;



@Service
@Transactional
@Validated
public class RangeInrService {

    @Autowired
    RangeInrRepository rangeInrRepository;

      public List<RangeInr> getAllRangeInrs() {
        return rangeInrRepository.findAll();
    }
    public RangeInr getRangeInrById(Long id){
          return rangeInrRepository.getReferenceById(id);
    }
}
