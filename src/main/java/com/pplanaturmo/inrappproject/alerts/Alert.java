package com.pplanaturmo.inrappproject.alerts;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.pplanaturmo.inrappproject.measurement.Measurement;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Modelo de datos de la entidad Alerta")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "alerts")
public class Alert {

    @Schema(description = "Identificador único de la alerta")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Identificador único de la medición que provoca la alerta")
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "measurement_id", referencedColumnName = "id")
    private Measurement measurement;

    @Schema(description = "Nivel de la alerta")
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private LevelEnum level;

    @Schema(description = "Booleano de si se ha revisado la alerta dosis")
    @Column(name = "revised", nullable = false)
    private Boolean revised;

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

    public enum LevelEnum {
        TOO_LOW,
        TOO_HIGH,
        DANGEROUS
    }
}
