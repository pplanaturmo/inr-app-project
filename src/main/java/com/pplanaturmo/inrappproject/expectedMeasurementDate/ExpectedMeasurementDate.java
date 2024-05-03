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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "measurements_dates")
public class ExpectedMeasurementDate {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
  private User user;

  @Column(name = "expected_date", nullable = false)
  @Temporal(TemporalType.DATE)
  private LocalDate expectedDate;

  @Column(name = "fullfilled_date", nullable = true)
  @Temporal(TemporalType.DATE)
  private LocalDate fullfilled_date;

  @Column(name = "fullfilled", nullable = false)
  private Boolean fulfilled;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;
}
