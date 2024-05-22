package com.pplanaturmo.inrappproject.role;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> findRoleByString(String roleName) {
        Role.UserRole userRoleEnum;
        try {
            userRoleEnum = Role.UserRole.valueOf(roleName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role name: " + roleName);
        }

        return roleRepository.findByRole(userRoleEnum);
    }

    // public void assignPatientRole(User user) {
    // Role role = roleRepository.findByRole(Role.UserRole.PATIENT)
    // .orElseThrow(() -> new RuntimeException("Patient role not found"));
    // user.setRoles(Collections.singleton(role));
    // }

    // public void assignProfessionalRole(User user) {
    // Role role = roleRepository.findByRole(Role.UserRole.PROFESSIONAL)
    // .orElseThrow(() -> new RuntimeException("Professional role not found"));
    // user.setRoles(Collections.singleton(role));
    // }

    // public void assignManagerRole(User user) {
    // Role role = roleRepository.findByRole(Role.UserRole.MANAGER)
    // .orElseThrow(() -> new RuntimeException("Manager role not found"));
    // user.setRoles(Collections.singleton(role));
    // }

    // public void assignAdminRole(User user) {
    // Role role = roleRepository.findByRole(Role.UserRole.ADMIN)
    // .orElseThrow(() -> new RuntimeException("Admin role not found"));
    // user.setRoles(Collections.singleton(role));
    // }

    // public void assignRole(UpdateUserRole updateUserRole) {
    // User user = userRepository.findById(updateUserRole.getUserId())
    // .orElseThrow(() -> new RuntimeException("User not found"));

    // Role.UserRole userRole =
    // Role.UserRole.valueOf(updateUserRole.getAssignedRole().toUpperCase());
    // Role role = roleRepository.findByRole(userRole)
    // .orElseThrow(() -> new RuntimeException(userRole.name() + " role not
    // found"));

    // user.setRoles(Collections.singleton(role));
    // userRepository.save(user);
    // }
}

// @Service
// @Transactional
// public class RoleService {

// @Autowired
// private RoleRepository roleRepository;

// public Role createRole(Role role) {
// return roleRepository.save(role);
// }

// public List<Role> getAllRoles() {
// return roleRepository.findAll();
// }

// public Optional<Role> getRoleById(Long id) {
// return roleRepository.findById(id);
// }

// public Role updateRole(Role role) {
// return roleRepository.save(role);
// }

// public void deleteRole(Long id) {
// roleRepository.deleteById(id);
// }

// }