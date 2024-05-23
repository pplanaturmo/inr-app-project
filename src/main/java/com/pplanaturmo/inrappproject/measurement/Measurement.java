package com.pplanaturmo.inrappproject.measurement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.pplanaturmo.inrappproject.alerts.Alert;
import com.pplanaturmo.inrappproject.dosePattern.DosePattern;
import com.pplanaturmo.inrappproject.user.User;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo de datos de la entidad Medición")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "measurements")
public class Measurement {

    @Schema(description = "Identificador único de la medición")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "ID del usuario que realiza la medición")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Schema(description = "Fecha de la medición")
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @Schema(description = "Resultado de la medición")
    @Column(name = "value", nullable = false)
    private Double value;

    @Schema(description = "Patrón de dosificación recomendado")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recommended_pattern", referencedColumnName = "id", nullable = false)
    private DosePattern recommendedPattern;

    @Schema(description = "Alerta relacionada con la medición")
    @OneToOne(mappedBy = "measurement", cascade = CascadeType.ALL)
    private Alert alert;

    @Schema(description = "Listado de valores de dosificación relacionados con la medición")
    @ElementCollection
    private Double[] dosagesValuesList;

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
