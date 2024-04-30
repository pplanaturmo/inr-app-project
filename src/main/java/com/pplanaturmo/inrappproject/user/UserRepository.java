package com.pplanaturmo.inrappproject.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   
    List<User> findByDepartmentId(Long departmentId);

    List<User> findByProfessionalId(Long professionalId);
    
    boolean existsByEmailAndIdNot(String email,Long id);
    
    boolean existsByIdCardAndIdNot(String idCard,Long id);
    
    boolean existsByHealthCardAndIdNot(String healthCard,Long id);

}
