package com.pplanaturmo.inrappproject.dosage;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.pplanaturmo.inrappproject.measurement.Measurement;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo de datos de la entidad Dosis")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dosages")
public class Dosage {

    @Schema(description = "Identificador único de la dosis")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Medida relacionada con la dosis")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_id", referencedColumnName = "id", nullable = false)
    private Measurement measurement;

    @Schema(description = "Fecha en la que tomar la dosis")
    @Column(name = "dose_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate doseDate;

    @Schema(description = "Booleano de si se ha tomado la dosis")
    @Column(name = "taken", nullable = false)
    private Boolean taken;

    @Schema(description = "Cantidad de la dosis")
    @Column(name = "dose", nullable = false)
    private Double doseValue;

    @Schema(description = "Marca de tiempo de creación")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Schema(description = "Marca de tiempo de actualización")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
