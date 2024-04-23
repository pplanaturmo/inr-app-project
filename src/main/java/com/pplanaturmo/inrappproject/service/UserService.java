package com.pplanaturmo.inrappproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.pplanaturmo.inrappproject.model.Department;
import com.pplanaturmo.inrappproject.model.Professional;
import com.pplanaturmo.inrappproject.model.User;
import com.pplanaturmo.inrappproject.repository.DepartmentRepository;
import com.pplanaturmo.inrappproject.repository.ProfessionalRepository;
import com.pplanaturmo.inrappproject.repository.UserRepository;

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
    private BCryptPasswordEncoder passwordEncoder;
    
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getUsersByDepartmentId(Long department_id) {
        return userRepository.findByDepartmentId(department_id);
    }

    public User assignDepartmentToUser(Long userId, Long department_id) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        if(department_id == -1){
            user.setDepartment(null);
        }else{

            Department department = departmentRepository.findById(department_id)
                    .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + department_id));
            user.setDepartment(department);
        }        

        return userRepository.save(user);
    }

    public User assignSupervisorToUser(Long userId, Long professionalId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        if(professionalId == -1){
            user.setSupervisor(null);
        }else{

            Professional professional = professionalRepository.findById(professionalId)
                    .orElseThrow(() -> new EntityNotFoundException("Professional not found with id: " + professionalId));
            user.setSupervisor(professional);
        }        
        
        return userRepository.save(user);
    }

}
