package com.pplanaturmo.inrappproject.user.dtos;

import org.springframework.format.annotation.NumberFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "DTO validación de creacióin de usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @Schema(description = "Contraseña de usuario recibida")
    @NotNull(message = "Password is required")
    @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres")
    private String password;

    @Schema(description = "Nombre de usuario recibido")
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    private String name;

    @Schema(description = "Apellidos de usuario recibidos")
    @NotNull(message = "Surname/s is required")
    @NotBlank(message = "Surname/s is required")
    private String surname;

    @Schema(description = "Correo electrónico del usuario recibido")
    @NotNull(message = "Email is required")
    @Email(message = "A valid email is required")
    private String email;

    @Schema(description = "Id el rango INR  del usuario recibido")
    @NumberFormat
    @NotNull
    private Long rangeInr;
    @Schema(description = "Id del patrón de dosificación del usuario recibido")
    @NumberFormat
    @NotNull
    private Long dosePattern;

    @Schema(description = "Consentimiento de tratamiento de datos del usuario recibido")
    @NotNull
    private String dataConsent;

}
