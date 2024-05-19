package com.pplanaturmo.inrappproject.professional;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.pplanaturmo.inrappproject.user.User;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo de datos de la entidad Profesional")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "professionals")
public class Professional {

    @Schema(description = "Identificador único del profesional")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Número de colegiado del profesional")
    @Column(name = "register_number", nullable = false)
    private String registerNumber;

    @Schema(description = "ID de usuario del profesional")
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Schema(description = "Tipo de profesional")
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TypeEnum type;

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

    @Schema(description = "Listado de usuarios supervisados por el profesional")
    @OneToMany(mappedBy = "supervisor", cascade = CascadeType.ALL)
    private List<User> supervisedUsers;

    public enum TypeEnum {
        MEDIC,
        NURSE
    }
}
