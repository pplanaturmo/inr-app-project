package com.pplanaturmo.inrappproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pplanaturmo.inrappproject.model.Department;
import com.pplanaturmo.inrappproject.repository.DepartmentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DepartmentService {

    
    @Autowired
    private DepartmentRepository departmentRepository;

    
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

   
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }


    public Department updateDepartment(Department user) {
        return departmentRepository.save(user);
    }

    public void deleteDepartmentById(Long id) {
        departmentRepository.deleteById(id);
    }


}
