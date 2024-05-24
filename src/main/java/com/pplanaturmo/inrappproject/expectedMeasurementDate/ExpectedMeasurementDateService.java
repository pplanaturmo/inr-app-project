package com.pplanaturmo.inrappproject.expectedMeasurementDate;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pplanaturmo.inrappproject.user.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ExpectedMeasurementDateService {

    @Autowired
    private ExpectedMeasurementDateRepository expectedMeasurementDateRepository;

    public void generateExpectedMeasurementDate(Integer days, User user, double value) {
        final Double TOO_DANGEROUS_VALUE = 7.0;

        ExpectedMeasurementDate newExpectedMeasurementDate = new ExpectedMeasurementDate();
        LocalDate currentDate = LocalDate.now();
        boolean seeDoctor = value > TOO_DANGEROUS_VALUE;
        newExpectedMeasurementDate.setUser(user);
        newExpectedMeasurementDate.setExpectedDate(currentDate.plusDays(days));
        newExpectedMeasurementDate.setFulfilled(false);
        newExpectedMeasurementDate.setContactDoctorASAP(seeDoctor);
        saveExpectedMeasurementDate(newExpectedMeasurementDate);
    }

    public ExpectedMeasurementDate saveExpectedMeasurementDate(ExpectedMeasurementDate expectedMeasurementDate) {
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

    public ExpectedMeasurementDate getLastExpectedMeasurementDateByUserId(Long userId) {
        return expectedMeasurementDateRepository.findTopByUserIdOrderByExpectedDateDesc(userId).orElse(null);
    }

}
