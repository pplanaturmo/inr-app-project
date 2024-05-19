package com.pplanaturmo.inrappproject.expectedMeasurementDate;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.pplanaturmo.inrappproject.user.User;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo de datos de Fecha Prevista de Medición")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "measurements_dates")
public class ExpectedMeasurementDate {

  @Schema(description = "Identificador único de las fechas previstas de medición")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Schema(description = "ID del usuario al que pertenece la fecha prevista")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
  private User user;

  @Schema(description = "Fecha esperada")
  @Column(name = "expected_date", nullable = false)
  @Temporal(TemporalType.DATE)
  private LocalDate expectedDate;

  @Schema(description = "Fecha de realización de la medición")
  @Column(name = "fullfilled_date", nullable = true)
  @Temporal(TemporalType.DATE)
  private LocalDate fullfilled_date;

  @Schema(description = "Valor booleano de Cumplida")
  @Column(name = "fullfilled", nullable = false)
  private Boolean fulfilled;

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
