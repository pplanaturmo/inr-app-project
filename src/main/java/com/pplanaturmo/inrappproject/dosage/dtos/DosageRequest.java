package com.pplanaturmo.inrappproject.dosage.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Schema(description = "DTO de validación de la dosis")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class DosageRequest {

        @Schema(description = "ID de la dosis a actualizar")
        @NotNull(message = "Dose ID is required")
        private Long id;
        @Schema(description = "Se ha tomado la dosis")
        @NotNull(message = "Dose 'taken' value is required")
        private Boolean taken;

    }

