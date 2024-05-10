package com.pplanaturmo.inrappproject.user.dtos;

import org.springframework.format.annotation.NumberFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotNull(message = "Password is required")
    @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres")
    private String password;

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Surname/s is required")
    @NotBlank(message = "Surname/s is required")
    private String surname;

    @NotNull(message = "Id card field is required")
    @NotBlank(message = "Id card field is required")
    private String idCard;

    @NotNull(message = "Health card is required")
    @NotBlank(message = "Health card is required")
    private String healthCard;

    @NotNull(message = "Email is required")
    @Email(message = "A valid email is required")
    private String email;

    @NumberFormat
    @NotNull
    private Long phone;

    @NotNull
    private String dataConsent;

}
