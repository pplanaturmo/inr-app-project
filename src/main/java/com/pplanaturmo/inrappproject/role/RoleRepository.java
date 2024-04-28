package com.pplanaturmo.inrappproject.role;

import com.pplanaturmo.inrappproject.user.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
    List<Role> findByUser(User user);
}
