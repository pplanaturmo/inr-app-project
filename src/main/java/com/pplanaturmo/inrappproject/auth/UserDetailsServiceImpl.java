package com.pplanaturmo.inrappproject.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pplanaturmo.inrappproject.user.User;
import com.pplanaturmo.inrappproject.user.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String idCard) throws UsernameNotFoundException {
        User user = userRepository.findByIdCard(idCard)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id card: " + idCard));

        return (UserDetails) UserDetailsImpl.build(user);
    }
}
