package com.pplanaturmo.inrappproject.auth.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotNull(message = "Id card is required")
    @NotBlank(message = "Id card is required")

    private String usernameOrEmail;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is required")
    private String password;

}
