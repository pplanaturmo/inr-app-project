package com.pplanaturmo.inrappproject.auth.dtos;

import java.util.List;

import com.pplanaturmo.inrappproject.role.Role;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticatedUserResponse {

    private String jwt;
    private Long id;
    private String name;
    private String surname;
    private List<Role> roles;
}
