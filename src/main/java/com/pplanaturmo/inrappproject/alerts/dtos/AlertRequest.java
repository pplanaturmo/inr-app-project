package com.pplanaturmo.inrappproject.alerts.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Schema(description = "DTO de validación de la alerta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlertRequest {
    @Schema(description = "ID de la alerta a actualizar")
    @NotNull(message = "Alert ID is required")
    private Long id;
    @Schema(description = "Booleano de si se ha revisado la alerta", required = true)
    private Boolean revised;
}
