package com.pplanaturmo.inrappproject.repository;

import com.pplanaturmo.inrappproject.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   
    List<User> findByDepartmentId(Long departmentId);
    
}
