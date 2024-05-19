package com.pplanaturmo.inrappproject.role;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Schema(description = "Modelo de datos de la entidad Rol")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Schema(description = "Identificador único del Rol")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Tipo de rol")
    private UserRole role;

    public enum UserRole {
        PATIENT,
        PROFESSIONAL,
        MANAGER,
        ADMIN
    }
}
