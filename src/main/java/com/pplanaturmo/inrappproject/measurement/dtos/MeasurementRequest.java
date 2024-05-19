package com.pplanaturmo.inrappproject.measurement.dtos;

import org.springframework.format.annotation.NumberFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "DTO de validación de la medición")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementRequest {

    @Schema(description = "Resultado de la medición enviado")
    @NumberFormat
    @NotNull(message = "Value is required")
    private Double value;

}
