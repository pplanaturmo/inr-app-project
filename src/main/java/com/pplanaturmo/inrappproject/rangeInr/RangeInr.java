package com.pplanaturmo.inrappproject.rangeInr;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Modelo de datos de la entidad Rango Terapeútico")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ranges_inr")
public class RangeInr {

    @Schema(description = "Identificador único del rango terapeútico")
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Schema(description = "Nivel minimo del rango terapeútico")
    @Column(name = "min_level", nullable = false)
    private Double minLevel;

    @Schema(description = "Nivel máximo del rango terapeútico")
    @Column(name = "max_level", nullable = false)
    private Double maxLevel;

    @Schema(description = "Descripción del rango terapeútico")
    @Column(name = "description", nullable = false)
    private String description;
}
