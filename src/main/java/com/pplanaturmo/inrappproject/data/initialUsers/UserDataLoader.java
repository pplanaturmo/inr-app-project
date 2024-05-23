package com.pplanaturmo.inrappproject.data.initialUsers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.pplanaturmo.inrappproject.dosePattern.DosePattern;
import com.pplanaturmo.inrappproject.dosePattern.DosePatternRepository;
import com.pplanaturmo.inrappproject.rangeInr.RangeInr;
import com.pplanaturmo.inrappproject.rangeInr.RangeInrRepository;
import com.pplanaturmo.inrappproject.role.Role;
import com.pplanaturmo.inrappproject.role.RoleService;
import com.pplanaturmo.inrappproject.user.User;
import com.pplanaturmo.inrappproject.user.UserRepository;

@Component
public class UserDataLoader {

    @Autowired
    private RangeInrRepository rangeInrRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private DosePatternRepository dosePatternRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    public void loadUser() throws Exception {

        User user = new User();
        user.setName("Patient");
        user.setSurname("Patient Surname");
        user.setEmail("patient@example.com");
        user.setDataConsent(true);
        user.setPassword(passwordEncoder.encode("12341234"));
        user.setUserRole("PATIENT");

        RangeInr rangeInr = rangeInrRepository.getReferenceById(1L);
        DosePattern dosePattern = dosePatternRepository.getReferenceById(15L);

        user.setRangeInr(rangeInr);
        user.setDosePattern(dosePattern);
        userRepository.save(user);

        // Optional<Role> patientRole = roleService.findRoleByString("PATIENT");
        // if (patientRole.isPresent()) {
        // user.setUserRole(patientRole.get());
        // }

        User user2 = new User();
        user2.setName("Professional");
        user2.setSurname("Professional Surname");
        user2.setEmail("professional@example.com");
        user2.setDataConsent(true);
        user2.setPassword(passwordEncoder.encode("12341234")); // Encoded password
        user2.setUserRole("PROFESSIONAL");
        RangeInr rangeInr2 = rangeInrRepository.getReferenceById(1L);
        DosePattern dosePattern2 = dosePatternRepository.getReferenceById(13L);
        user2.setRangeInr(rangeInr2);
        user2.setDosePattern(dosePattern2);
        userRepository.save(user2);

        User user3 = new User();
        user3.setName("Manager");
        user3.setSurname("Manager Surname");
        user3.setEmail("manager@example.com");
        user3.setDataConsent(true);
        user3.setPassword(passwordEncoder.encode("12341234")); // Encoded password
        user3.setUserRole("MANAGER");
        RangeInr rangeInr3 = rangeInrRepository.getReferenceById(1L);
        DosePattern dosePattern3 = dosePatternRepository.getReferenceById(13L);
        user3.setRangeInr(rangeInr3);
        user3.setDosePattern(dosePattern3);
        userRepository.save(user3);

        User user4 = new User();
        user4.setName("Admin");
        user4.setSurname("Admin Surname");
        user4.setEmail("admin@example.com");
        user4.setDataConsent(true);
        user4.setPassword(passwordEncoder.encode("12341234")); // Encoded password
        user4.setUserRole("ADMIN");
        RangeInr rangeInr4 = rangeInrRepository.getReferenceById(1L);
        DosePattern dosePattern4 = dosePatternRepository.getReferenceById(13L);
        user4.setRangeInr(rangeInr4);
        user4.setDosePattern(dosePattern4);
        userRepository.save(user4);
    }
}
