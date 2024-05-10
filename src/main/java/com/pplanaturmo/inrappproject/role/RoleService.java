package com.pplanaturmo.inrappproject.role;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<Role> getRolesByUser(User user) {
        return roleRepository.findByUser(user);
    }

    public void defaultRole(User user) {
        Role defaultRole = new Role();
        defaultRole.setUser(user);
        defaultRole.setAssignedRole(UserRole.PATIENT);
        roleRepository.save(defaultRole);
        List<Role> roles = new ArrayList<>();
        roles.add(defaultRole);

    }
}