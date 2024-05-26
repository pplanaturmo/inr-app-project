package com.pplanaturmo.inrappproject.alerts.dtos;

import com.pplanaturmo.inrappproject.alerts.Alert.LevelEnum;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

@Schema(description = "DTO de respuesta de alertas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlertResponse {
    @Schema(description = "ID de la alerta")
    @NumberFormat
    private Long id;

    @Schema(description = "Id del usuario al que pertenece la alerta")
    private Long userId;

    @Schema(description = "Id del usuario al que pertenece la alerta")
    private String userName;

    @Schema(description = "Id del usuario al que pertenece la alerta")
    private String userSurname;

    @Schema(description = "Id del usuario al que pertenece la alerta")
    private String userEmail;

    @Schema(description = "Fecha de creación de la alerta")
    private LocalDateTime date;

    @Schema(description = "Nivel de la alerta")
    private LevelEnum level;

}