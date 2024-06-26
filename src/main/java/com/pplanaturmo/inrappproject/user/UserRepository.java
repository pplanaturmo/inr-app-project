package com.pplanaturmo.inrappproject.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pplanaturmo.inrappproject.dosePattern.DosePattern;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByDepartmentId(Long departmentId);

    List<User> findBySupervisorId(Long supervisorId);

    List<User> findByDosePattern(DosePattern dosePattern);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    // Optional<User> findByIdCard(String idCard);

    // boolean existsByIdCardAndIdNot(String idCard, Long id);

    // boolean existsByHealthCardAndIdNot(String healthCard, Long id);

    // boolean existsByIdCard(String idCard);

    // boolean existsByHealthCard(String healthCard);

}
