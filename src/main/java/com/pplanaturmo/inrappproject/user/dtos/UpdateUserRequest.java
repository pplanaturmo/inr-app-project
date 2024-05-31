package com.pplanaturmo.inrappproject.user.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;


@Schema(description = "DTO validación de actualización de usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {


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

        @Schema(description = "Id del rango INR  del usuario recibido")
        @NotNull
        private Long rangeInr;
        @Schema(description = "Id del patrón de dosificación del usuario recibido")
        @NotNull
        private Long dosePattern;


    }


