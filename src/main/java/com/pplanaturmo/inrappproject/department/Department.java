package com.pplanaturmo.inrappproject.department;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.pplanaturmo.inrappproject.professional.Professional;
import com.pplanaturmo.inrappproject.user.User;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo de datos de la entidad departamento")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "departments")
public class Department {

    @Schema(description = "Identificador único de departamento")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nombre del departamento")
    @NotNull
    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Schema(description = "Ciudad del departamento")
    @NotNull
    @NotBlank
    @Column(name = "city", nullable = false)
    private String city;

    @Schema(description = "Responsable del departamento")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", referencedColumnName = "id", nullable = false)
    private Professional professional;

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

    @Schema(description = "Lista de usuarios pertenecientes al departamento")
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<User> users;
}
