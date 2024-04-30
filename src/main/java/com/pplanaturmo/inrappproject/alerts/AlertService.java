package com.pplanaturmo.inrappproject.alerts;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Alert createAlert(Alert alert) {
        return alertRepository.save(alert);
    }

    public Alert getAlertById(Long id) {
        return alertRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alert not found with id: " + id));
    }

    public List<Alert> getAlertsByDepartment(Long departmentId){
        List<User> departmentUsers = userService.getUsersByDepartmentId(departmentId);
        List<Alert> departmentAlerts = departmentUsers.stream()
                .flatMap(user -> measurementService.getMeasurementsByUserId(user.getId()).stream()
                        .map(Measurement::getAlert)
                        .filter(alert -> alert != null))
                .distinct()
                .collect(Collectors.toList());
        return departmentAlerts;
    }

    public List<Alert> getAlertsByProfessional(Long professionalId){
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

}
