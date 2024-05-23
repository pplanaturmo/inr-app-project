package com.pplanaturmo.inrappproject.dosage.dtos;

import com.pplanaturmo.inrappproject.observation.dtos.ObservationRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;


@Schema(description = "DTO de respuesta de dósis")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DosageResponse {

    @Schema(description = "ID de la dosis")
    @NumberFormat

    private Long id;
    @Schema(description = "Valor de la dosis")
    private Double value;

    @Schema(description = "Fecha de toma de la dosis")
    private Date date;

    @Schema(description = "Se ha tomado la dosis")
    private Boolean taken;

}
