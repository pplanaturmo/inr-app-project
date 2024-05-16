package com.pplanaturmo.inrappproject.data.initialUsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.pplanaturmo.inrappproject.dosePattern.DosePattern;
import com.pplanaturmo.inrappproject.dosePattern.DosePatternRepository;
import com.pplanaturmo.inrappproject.rangeInr.RangeInr;
import com.pplanaturmo.inrappproject.rangeInr.RangeInrRepository;
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

    public void loadUser() throws Exception {

        User user = new User();
        user.setName("RandomName");
        user.setSurname("RandomSurname");
        user.setIdCard("RandomIdCard");
        user.setHealthCard("RandomHealthCard");
        user.setEmail("admin@example.com");
        user.setPhone(1234567890L);
        user.setDataConsent(true);
        user.setPassword(passwordEncoder.encode("12341234")); // Encoded password

        // Fetch the RangeInr and DosePattern entities
        RangeInr rangeInr = rangeInrRepository.getReferenceById(1L);
        DosePattern dosePattern = dosePatternRepository.getReferenceById(15L);

        // Set the fetched entities
        user.setRangeInr(rangeInr);
        user.setDosePattern(dosePattern);

        // Persist the user entity
        userRepository.save(user);
    }
}
