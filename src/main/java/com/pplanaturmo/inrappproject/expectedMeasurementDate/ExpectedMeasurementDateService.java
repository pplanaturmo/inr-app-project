package com.pplanaturmo.inrappproject.expectedMeasurementDate;

import java.util.Calendar;
import java.util.Date;
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

    public void generateExpectedMeasurementDate(Integer days, User user){
            ExpectedMeasurementDate newExpectedMeasurementDate = new ExpectedMeasurementDate();

            Date currentDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.add(Calendar.DAY_OF_MONTH, days);

            newExpectedMeasurementDate.setUser(user);
            newExpectedMeasurementDate.setExpectedDate(calendar.getTime());
            newExpectedMeasurementDate.setFulfilled(false);
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

   
}
