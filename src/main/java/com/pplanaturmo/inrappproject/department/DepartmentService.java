package com.pplanaturmo.inrappproject.department;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pplanaturmo.inrappproject.professional.Professional;
import com.pplanaturmo.inrappproject.professional.ProfessionalRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class DepartmentService {

    
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ProfessionalRepository professionalRepository;

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

    
    public Department assignManagerToDepartment(Long departmentId, Long professionalId) {
      
                Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + departmentId));
    

        if(professionalId == -1){
            department.setProfessional(null);
        }else{

            Professional professional = professionalRepository.findById(professionalId)
                    .orElseThrow(() -> new EntityNotFoundException("Professional not found with id: " + professionalId));
            department.setProfessional(professional);
        }        
        
        return departmentRepository.save(department);
    }

    public void deleteDepartmentById(Long id) {
        departmentRepository.deleteById(id);
    }


}
