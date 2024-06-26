package com.pplanaturmo.inrappproject.dosePattern;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Model de datos de la entidad Patrón de Dosificación")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dose_patterns")
public class DosePattern {

    @Schema(description = "Identificador único del patrón de dosificación")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Listado de valores del patrón de dosificación")
    @ElementCollection
    private Double[] patternValue;

    @Schema(description = "Nivel del patrón de dosificación")
    @Column(name = "level", nullable = false)
    private Integer level;

    @Schema(description = "Tipo de medicamento del patrón de dosificación")
    @Column(name = "drug", nullable = false)
    @Enumerated(EnumType.STRING)
    private DrugTypeEnum drug;

    public enum DrugTypeEnum {
        SINTROM_4,
        SINTROM_1
    }

    public DosePattern(Integer level, Double[] patternValue, DrugTypeEnum drug) {
        this.patternValue = patternValue;
        this.level = level;
        this.drug = drug;
    }
}
