package com.pplanaturmo.inrappproject.user.dtos;

import org.springframework.format.annotation.NumberFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "DTO de validación de actualización de supervisor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserSupervisor {

    @Schema(description = "ID del usuario a actualizar")
    @NumberFormat
    @NotNull(message = "User id is required")
    @NotBlank(message = "User id is required")
    private Long userId;

    @Schema(description = "ID del profesional a asignar")
    @NumberFormat
    @NotNull(message = "Professional Id is required")
    @NotBlank(message = "Professional Id is required")
    private Long professionalId;

}
