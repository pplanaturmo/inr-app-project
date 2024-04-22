package com.pplanaturmo.inrappproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pplanaturmo.inrappproject.model.ExpectedMeasurementDate;
import com.pplanaturmo.inrappproject.repository.ExpectedMeasurementDateRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ExpectedMeasurementDateService {

    @Autowired
    private ExpectedMeasurementDateRepository expectedMeasurementDateRepository;

    

    public ExpectedMeasurementDate save(ExpectedMeasurementDate expectedMeasurementDate) {
        return expectedMeasurementDateRepository.save(expectedMeasurementDate);
    }

    public ExpectedMeasurementDate findById(Long id) {
        return expectedMeasurementDateRepository.findById(id).orElse(null);
    }

    public List<ExpectedMeasurementDate> findAll() {
        return expectedMeasurementDateRepository.findAll();
    }

    public ExpectedMeasurementDate updateExpectedMeasurementDate(ExpectedMeasurementDate expectedMeasurementDate) {
        return expectedMeasurementDateRepository.save(expectedMeasurementDate);
    }

    public void deleteById(Long id) {
        expectedMeasurementDateRepository.deleteById(id);
    }



    

    public List<ExpectedMeasurementDate> findByUserId(Long userId) {
        return expectedMeasurementDateRepository.findByUserId(userId);
    }

    public List<ExpectedMeasurementDate> findByFulfilled(Boolean fulfilled) {
        return expectedMeasurementDateRepository.findByFulfilled(fulfilled);
    }

    public List<ExpectedMeasurementDate> findByMismatchedDates() {
        return expectedMeasurementDateRepository.findByMismatchedDates();
    }
}
