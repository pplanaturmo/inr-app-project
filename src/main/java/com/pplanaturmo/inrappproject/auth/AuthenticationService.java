package com.pplanaturmo.inrappproject.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pplanaturmo.inrappproject.auth.dtos.AuthenticatedUserResponse;
import com.pplanaturmo.inrappproject.auth.dtos.AuthenticationRequest;
import com.pplanaturmo.inrappproject.auth.token.JwtService;
import com.pplanaturmo.inrappproject.auth.token.Token;
import com.pplanaturmo.inrappproject.auth.token.TokenRepository;
import com.pplanaturmo.inrappproject.auth.token.TokenType;
import com.pplanaturmo.inrappproject.role.Role;
import com.pplanaturmo.inrappproject.role.RoleService;
import com.pplanaturmo.inrappproject.user.User;
import com.pplanaturmo.inrappproject.user.UserRepository;
import com.pplanaturmo.inrappproject.user.UserService;
import com.pplanaturmo.inrappproject.user.dtos.UpdateUserRole;
import com.pplanaturmo.inrappproject.user.dtos.UserRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticatedUserResponse register(UserRequest userRequest) {

        var user = User.builder()
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .dataConsent(Boolean.parseBoolean(userRequest.getDataConsent()))
                .build();

        userService.validateUniqueEmail(user);

        user.setUserRole("PATIENT");




        var savedUser = userRepository.save(user);

        userService.assignInrToUser(savedUser.getId(),userRequest.getRangeInr());
        userService.assignDosePatternToUser(savedUser.getId(),userRequest.getDosePattern());

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticatedUserResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .id(savedUser.getId())
                .name(savedUser.getName())
                .surname(savedUser.getSurname())
                .department(user.getDepartment() != null ? user.getDepartment().getId() : null)
                .supervisor(user.getSupervisor() != null ? user.getSupervisor().getId() : null)
                .rangeInr(user.getRangeInr() != null ? user.getRangeInr().getId() : null)
                .dosePattern(user.getDosePattern() != null ? user.getDosePattern().getId() : null)
                .role(user.getUserRole())
                .build();
    }
/*
    public AuthenticatedUserResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()));
        var user = userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticatedUserResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .department(user.getDepartment() != null ? user.getDepartment().getId() : null)
                .supervisor(user.getSupervisor() != null ? user.getSupervisor().getId() : null)
                .rangeInr(user.getRangeInr() != null ? user.getRangeInr().getId() : null)
                .dosePattern(user.getDosePattern() != null ? user.getDosePattern().getId() : null)
                .role(user.getUserRole())
                .build();
    }*/

    public AuthenticatedUserResponse authenticate(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()));
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw e; // You might want to handle this differently
        }

        var user = userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return AuthenticatedUserResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .department(user.getDepartment() != null ? user.getDepartment().getId() : null)
                .supervisor(user.getSupervisor() != null ? user.getSupervisor().getId() : null)
                .rangeInr(user.getRangeInr() != null ? user.getRangeInr().getId() : null)
                .dosePattern(user.getDosePattern() != null ? user.getDosePattern().getId() : null)
                .role(user.getUserRole())
                .build();
    }

    public void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticatedUserResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}