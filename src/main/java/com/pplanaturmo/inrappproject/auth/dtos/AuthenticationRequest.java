package com.pplanaturmo.inrappproject.auth.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "DTO de petición de autenticación")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @Schema(description = "Correo electrónico de la petición")
    private String email;

    @Schema(description = "Contraseña de la petición")
    String password;
}