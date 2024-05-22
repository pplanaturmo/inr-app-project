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
        user.setPassword(passwordEncoder.encode("12341234")); // Encoded password

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

        RangeInr rangeInr2 = rangeInrRepository.getReferenceById(1L);
        DosePattern dosePattern2 = dosePatternRepository.getReferenceById(13L);

        user2.setRangeInr(rangeInr2);
        user2.setDosePattern(dosePattern2);
        // Optional<Role> patientRole2 = roleService.findRoleByString("Professional");
        // if (patientRole.isPresent()) {
        // user.setUserRole(patientRole2.get());
        // } else {
        // user.setUserRole(null);
        // }
        userRepository.save(user);
    }
}
