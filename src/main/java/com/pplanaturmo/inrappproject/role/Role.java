package com.pplanaturmo.inrappproject.role;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UserRole role;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public enum UserRole {
        PATIENT,
        PROFESSIONAL,
        MANAGER,
        ADMIN
    }
}

// @Id
// @GeneratedValue(strategy = GenerationType.IDENTITY)
// private Long id;

// @Enumerated(EnumType.STRING)
// @Column(name = "role", nullable = false)
// private UserRole assignedRole;

// @ManyToOne(fetch = FetchType.LAZY)
// @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
// private User user;
