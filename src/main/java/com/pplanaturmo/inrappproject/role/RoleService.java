package com.pplanaturmo.inrappproject.role;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pplanaturmo.inrappproject.role.Role.UserRole;
import com.pplanaturmo.inrappproject.user.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    public void assignPatientRole(User user) {
        Role role = roleRepository.findByRole(Role.UserRole.PATIENT)
                .orElseThrow(() -> new RuntimeException("Patient role not found"));
        user.setRoles(Collections.singleton(role));
    }

    public void assignProfessionalRole(User user) {
        Role role = roleRepository.findByRole(Role.UserRole.PROFESSIONAL)
                .orElseThrow(() -> new RuntimeException("Professional role not found"));
        user.setRoles(Collections.singleton(role));
    }

    public void assignManagerRole(User user) {
        Role role = roleRepository.findByRole(Role.UserRole.MANAGER)
                .orElseThrow(() -> new RuntimeException("Manager role not found"));
        user.setRoles(Collections.singleton(role));
    }

    public void assignAdminRole(User user) {
        Role role = roleRepository.findByRole(Role.UserRole.ADMIN)
                .orElseThrow(() -> new RuntimeException("Admin role not found"));
        user.setRoles(Collections.singleton(role));
    }

}