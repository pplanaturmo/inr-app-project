package com.pplanaturmo.inrappproject.alerts;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pplanaturmo.inrappproject.alerts.Alert.LevelEnum;
import com.pplanaturmo.inrappproject.alerts.dtos.AlertResponse;
import com.pplanaturmo.inrappproject.measurement.Measurement;
import com.pplanaturmo.inrappproject.measurement.MeasurementService;
import com.pplanaturmo.inrappproject.user.User;
import com.pplanaturmo.inrappproject.user.UserService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MeasurementService measurementService;

    public void createAlertIfNeeded(Measurement measurement) {

        final Double VERY_LOW_VALUE = 1.4;
        final Double HIGH_VALUE = 4.9;
        final Double VERY_HIGH_VALUE = 7.0;

        Double value = measurement.getValue();

        if (value < VERY_LOW_VALUE || value > HIGH_VALUE) {
            Alert newAlert = new Alert();
            newAlert.setMeasurement(measurement);
            if (value < VERY_LOW_VALUE) {
                newAlert.setLevel(LevelEnum.TOO_LOW);
            } else if (value < VERY_HIGH_VALUE) {
                newAlert.setLevel(LevelEnum.TOO_HIGH);
            } else {
                newAlert.setLevel(LevelEnum.DANGEROUS);
            }

            createAlert(newAlert);
        }

    }

    public Alert createAlert(Alert alert) {
        return alertRepository.save(alert);
    }

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    public Alert getAlertById(Long id) {
        return alertRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alert not found with id: " + id));
    }

    public List<Alert> getAlertsByDepartment(Long departmentId) {
        List<User> departmentUsers = userService.getUsersByDepartmentId(departmentId);
        List<Alert> departmentAlerts = departmentUsers.stream()
                .flatMap(user -> measurementService.getMeasurementsByUserId(user.getId()).stream()
                        .map(Measurement::getAlert)
                        .filter(alert -> alert != null))
                .distinct()
                .collect(Collectors.toList());
        return departmentAlerts;
    }

    public List<Alert> getAlertsByProfessional(Long professionalId) {
        List<User> professionalUsers = userService.getUsersByProfessionalId(professionalId);
        List<Alert> departmentAlerts = professionalUsers.stream()
                .flatMap(user -> measurementService.getMeasurementsByUserId(user.getId()).stream()
                        .map(Measurement::getAlert)
                        .filter(alert -> alert != null))
                .distinct()
                .collect(Collectors.toList());
        return departmentAlerts;
    }

    public Alert updateAlert(Alert alert) {
        return alertRepository.save(alert);
    }

    public void deleteAlertById(Long id) {
        alertRepository.deleteById(id);
    }

    public AlertResponse convertToResponse(Alert alert) {
        AlertResponse alertResponse = AlertResponse.builder().id(alert.getId())
                .userId(alert.getMeasurement().getUser().getId())
                .userName(alert.getMeasurement().getUser().getName())
                .userSurname(alert.getMeasurement().getUser().getSurname())
                .userEmail(alert.getMeasurement().getUser().getEmail())
                .date(alert.getCreatedAt())
                .level(alert.getLevel())
                .build();

        return alertResponse;
    }

    public List<AlertResponse> convertToAlertResponseList(List<Alert> alerts) {
        return alerts.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public List<AlertResponse> convertToPendingList(List<Alert> alerts) {
        return alerts.stream()
                .filter(alert -> !alert.getRevised())
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
}
