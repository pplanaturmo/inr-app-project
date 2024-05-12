package com.pplanaturmo.inrappproject.data.initialRoles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pplanaturmo.inrappproject.role.Role;
import com.pplanaturmo.inrappproject.role.RoleRepository;

@Component
public class InitialRoles {

    @Autowired
    private RoleRepository roleRepository;

    public void loadRoles() {
        addRole(Role.UserRole.PATIENT);
        addRole(Role.UserRole.PROFESSIONAL);
        addRole(Role.UserRole.MANAGER);
        addRole(Role.UserRole.ADMIN);
    }

    private void addRole(Role.UserRole userRole) {
        Role role = new Role();
        role.setRole(userRole);
        roleRepository.save(role);
    }

}
