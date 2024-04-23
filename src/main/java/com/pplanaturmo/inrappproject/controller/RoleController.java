package com.pplanaturmo.inrappproject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pplanaturmo.inrappproject.dto.UpdateUserRole;
import com.pplanaturmo.inrappproject.model.Role;
import com.pplanaturmo.inrappproject.model.User;
import com.pplanaturmo.inrappproject.service.RoleService;
import com.pplanaturmo.inrappproject.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public Role createRole(@Valid @RequestBody UpdateUserRole updateUserRole) {
        UserService userService = new UserService();
        Role newRole = new Role();
        
        User user = userService.getUserById(updateUserRole.getUserId());
        Role.UserRole assignedRole = Role.UserRole.valueOf(updateUserRole.getAssignedRole().toUpperCase());
        newRole.setUser(user);
        newRole.setAssignedRole(assignedRole);
        return roleService.createRole(newRole);
    }
    
    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public Optional<Role> getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @PutMapping("/{id}")
    public Role updateRole(@PathVariable Long id, @RequestBody Role role) {
        role.setId(id);
        return roleService.updateRole(role);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
    }

}