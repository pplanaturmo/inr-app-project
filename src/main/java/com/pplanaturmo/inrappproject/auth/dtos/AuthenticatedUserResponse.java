package com.pplanaturmo.inrappproject.auth.dtos;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pplanaturmo.inrappproject.role.Role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "DTO de respuesta de autenticación")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticatedUserResponse {

    @Schema(description = "Token de acceso")
    @JsonProperty("access_token")
    private String accessToken;

    @Schema(description = "Token de refresco")
    @JsonProperty("refresh_token")
    private String refreshToken;

    @Schema(description = "ID del usuario")
    private Long id;

    @Schema(description = "Nombre del usuario")
    private String name;

    @Schema(description = "Apellidos del usuario")
    private String surname;

    @Schema(description = "ID del departamento del usuario")
    private Long department;

    @Schema(description = "ID del supervisor del usuario")
    private Long supervisor;

    @Schema(description = "ID del rango terapéutico del usuario")
    private Long rangeInr;

    @Schema(description = "ID del patrón de dosificación del usuario")
    private Long dosePattern;

    @Schema(description = "Listado de roles del usuario")
    private Set<Role> roles;

}
