package com.pplanaturmo.inrappproject.user.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;


@Schema(description = "DTO de validación de actualización de contraseña")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePassword {

    @Schema(description = "Nueva contraseña")
    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is required")
    private String newPassword;

}
