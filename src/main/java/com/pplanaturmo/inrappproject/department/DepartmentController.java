package com.pplanaturmo.inrappproject.department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pplanaturmo.inrappproject.dto.DepartmentRequest;
import com.pplanaturmo.inrappproject.dto.UpdateDepartmentManager;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/create")
    public Department createDepartment(@Valid @RequestBody DepartmentRequest createDepartmentRequest) {

        Department department = convertToDepartment(createDepartmentRequest);

        return departmentService.saveDepartment(department);
    }

    private Department convertToDepartment(DepartmentRequest createDepartmentRequest) {
        Department department = new Department();

        department.setName(createDepartmentRequest.getName());
        department.setCity(createDepartmentRequest.getCity());

        return department;
    }

    @GetMapping("/")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PutMapping("/{departmentId}/manager")
    public Department assignManagerToDepartment(@PathVariable("departmentId") @Valid @NotNull Long profId,
            @RequestBody @Valid UpdateDepartmentManager updateDepartmentManager) {
        Long departmentId = updateDepartmentManager.getManagerId();
        return departmentService.assignManagerToDepartment(departmentId, profId);
    }

    @DeleteMapping("/{departmentId}")
    public void deleteUser(@Valid @PathVariable("departmentId") @NotNull Long departmentId) {
        departmentService.deleteDepartmentById(departmentId);
    }

}
