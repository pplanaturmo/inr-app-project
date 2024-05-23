package com.pplanaturmo.inrappproject.observation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.pplanaturmo.inrappproject.measurement.Measurement;
import com.pplanaturmo.inrappproject.user.User;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo de datos de la entidad Observación")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "observations")
public class Observation {

    @Schema(description = "Identificador único de la observación")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Usuario creador de la observación")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Schema(description = "Medida relacionada con la observación")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_id", referencedColumnName = "id")
    private Measurement measurement;

    @Schema(description = "Fecha de la observación")
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @Schema(description = "Causa de la observación")
    @Enumerated(EnumType.STRING)
    @Column(name = "cause", nullable = false)
    private CauseEnum cause;

    @Schema(description = "Descripción de la observación")
    @Column(name = "description", nullable = true, columnDefinition = "LONGTEXT")
    private String description;

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

    public enum CauseEnum {
        NO_DOSE,
        DOUBLE_DOSE,
        BLEEDING,
        DIET,
        DRUG,
        OTHER
    }
}
