package com.pplanaturmo.inrappproject.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.pplanaturmo.inrappproject.department.Department;
import com.pplanaturmo.inrappproject.department.DepartmentRepository;
import com.pplanaturmo.inrappproject.professional.Professional;
import com.pplanaturmo.inrappproject.professional.ProfessionalRepository;
import com.pplanaturmo.inrappproject.rangeInr.RangeInr;
import com.pplanaturmo.inrappproject.rangeInr.RangeInrRepository;
import com.pplanaturmo.inrappproject.user.exceptions.AlreadyExistsException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
@Validated
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ProfessionalRepository professionalRepository;

    @Autowired
    private RangeInrRepository rangeInrRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User createUser(User user) {
        validateUniqueFields(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Checks that the three fields are unique in the database
    private void validateUniqueFields(User user) {
        if (user.getEmail() != null && userRepository.existsByEmailAndIdNot(user.getEmail(), user.getId())) {
            throw new AlreadyExistsException("Email already exists: " + user.getEmail());
        }
        if (user.getIdCard() != null && userRepository.existsByIdCardAndIdNot(user.getIdCard(), user.getId())) {
            throw new AlreadyExistsException("ID card already exists: " + user.getIdCard());
        }
        if (user.getHealthCard() != null
                && userRepository.existsByHealthCardAndIdNot(user.getHealthCard(), user.getId())) {
            throw new AlreadyExistsException("Health card already exists: " + user.getHealthCard());
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(User user) {
        validateUniqueFields(user);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getUsersByDepartmentId(Long department_id) {
        return userRepository.findByDepartmentId(department_id);
    }

    public List<User> getUsersByProfessionalId(Long professionalId) {
        return userRepository.findByProfessionalId(professionalId);
    }

    public User assignDepartmentToUser(Long userId, Long department_id) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        if (department_id == -1) {
            user.setDepartment(null);
        } else {

            Department department = departmentRepository.findById(department_id)
                    .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + department_id));
            user.setDepartment(department);
        }

        return userRepository.save(user);
    }

    public User assignSupervisorToUser(Long userId, Long professionalId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        if (professionalId == -1) {
            user.setSupervisor(null);
        } else {

            Professional professional = professionalRepository.findById(professionalId)
                    .orElseThrow(
                            () -> new EntityNotFoundException("Professional not found with id: " + professionalId));
            user.setSupervisor(professional);
        }

        return userRepository.save(user);
    }

    public User setUserInrRange(Long userId, Long rangeInrId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        if (rangeInrId == -1) {
            user.setRangeInr(null);
        } else {

            RangeInr rangeInr = rangeInrRepository.findById(rangeInrId)
                    .orElseThrow(() -> new EntityNotFoundException("Range of INR not found with id: " + rangeInrId));
            user.setRangeInr(rangeInr);
        }

        return userRepository.save(user);
    }

}
