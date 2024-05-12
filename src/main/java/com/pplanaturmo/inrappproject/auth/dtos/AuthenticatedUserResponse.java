package com.pplanaturmo.inrappproject.auth.dtos;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pplanaturmo.inrappproject.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticatedUserResponse {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    private Long id;
    private String name;
    private String surname;
    private Set<Role> roles;
}
