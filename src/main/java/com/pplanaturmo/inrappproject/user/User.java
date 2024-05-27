package com.pplanaturmo.inrappproject.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pplanaturmo.inrappproject.department.Department;
import com.pplanaturmo.inrappproject.dosePattern.DosePattern;
import com.pplanaturmo.inrappproject.expectedMeasurementDate.ExpectedMeasurementDate;
import com.pplanaturmo.inrappproject.measurement.Measurement;
import com.pplanaturmo.inrappproject.observation.Observation;
import com.pplanaturmo.inrappproject.professional.Professional;
import com.pplanaturmo.inrappproject.rangeInr.RangeInr;
import com.pplanaturmo.inrappproject.role.Role;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo de datos de la entidad Usuario")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Schema(description = "Identificador único del usuario")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Área o servicio al que esta adscrito el usuario")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @Schema(description = "Profesional que supervisa al usuario si lo hubiere")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supervisor_id", referencedColumnName = "id")
    private Professional supervisor;

    @Schema(description = "Contraseña del usuario")
    @Column(name = "password", nullable = false)
    private String password;

    @Schema(description = "Nombre del usuario")
    @Column(name = "name", nullable = false)
    private String name;

    @Schema(description = "Apellidos del usuario")
    @Column(name = "surname", nullable = false)
    private String surname;

    @Schema(description = "Correo eléctronico del usuario")
    @Column(name = "email", nullable = false)
    private String email;

    @Schema(description = "Consentimiento de tratamiento estadístico de datos del usuario")
    @Column(name = "data_consent", nullable = false)
    private Boolean dataConsent;

    @Schema(description = "Rango terapéutico de INR del usuario")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ranges_inr", referencedColumnName = "id")
    private RangeInr rangeInr;

    @Schema(description = "Patrón de dosificación del usuario")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dose_pattern_id", referencedColumnName = "id")
    private DosePattern dosePattern;

    @Schema(description = "Rol del usuario")
    @Column(name = "role_id", nullable = false)
    private String userRole;

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

    @Schema(description = "Listado de medidas tomadas por el usuario")
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Measurement> measurements;

    @Schema(description = "Listado de fechas previstas de toma de medida del usuario")
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ExpectedMeasurementDate> expectedMeasurementDates;

    @Schema(description = "Listado de observaciones del usario")
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Observation> observations;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + userRole));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // @Schema(description = "Rol del usuario")
    // @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // @JoinColumn(name = "role_id", referencedColumnName = "id")
    // private Role userRole;

    // @Schema(description = "Listado de roles del usuario")
    // @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id",
    // referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name =
    // "role_id", referencedColumnName = "id"))
    // private Set<Role> roles;

    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {
    // if (userRole == null) {
    // return Collections.emptyList();
    // }

    // return Collections.singletonList(new SimpleGrantedAuthority(userRole));
    // }

    // @Override
    // public String getUsername() {
    // return email;
    // }

    // @Override
    // public String getPassword() {
    // return password;
    // }

    // @Override
    // public boolean isAccountNonExpired() {
    // return true;
    // }

    // @Override
    // public boolean isAccountNonLocked() {
    // return true;
    // }

    // @Override
    // public boolean isCredentialsNonExpired() {
    // return true;
    // }

    // @Override
    // public boolean isEnabled() {
    // return true;
    // }

    // ---------------------------------------
    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {

    // if (roles == null) {
    // return Collections.emptyList(); // Return an empty list if roles is null
    // }
    // return roles.stream()
    // .map(role -> new SimpleGrantedAuthority(role.toString()))
    // .collect(Collectors.toList());
    // }

    // @Override
    // public String getUsername() {
    // return email;
    // }

    // @Override
    // public String getPassword() {
    // return password;
    // }

    // @Override
    // public boolean isAccountNonExpired() {
    // return true;
    // }

    // @Override
    // public boolean isAccountNonLocked() {
    // return true;
    // }

    // @Override
    // public boolean isCredentialsNonExpired() {
    // return true;
    // }

    // @Override
    // public boolean isEnabled() {
    // return true;
    // }

    // public void addRole(Role role) {
    // this.roles.add(role);
    // }

    // public void removeRole(Role role) {
    // this.roles.remove(role);
    // }

    // public boolean hasRole(Role.UserRole userRole) {
    // return roles.stream().anyMatch(role -> role.getRole().equals(userRole));
    // }

}

// package com.pplanaturmo.inrappproject.user;

// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;
// import jakarta.persistence.*;

// import java.time.LocalDateTime;
// import java.util.Collection;
// import java.util.Collections;
// import java.util.List;
// import java.util.Set;
// import java.util.stream.Collectors;

// import org.hibernate.annotations.CreationTimestamp;
// import org.hibernate.annotations.UpdateTimestamp;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import com.pplanaturmo.inrappproject.department.Department;
// import com.pplanaturmo.inrappproject.dosePattern.DosePattern;
// import
// com.pplanaturmo.inrappproject.expectedMeasurementDate.ExpectedMeasurementDate;
// import com.pplanaturmo.inrappproject.measurement.Measurement;
// import com.pplanaturmo.inrappproject.observation.Observation;
// import com.pplanaturmo.inrappproject.professional.Professional;
// import com.pplanaturmo.inrappproject.rangeInr.RangeInr;
// import com.pplanaturmo.inrappproject.role.Role;

// import io.swagger.v3.oas.annotations.media.Schema;

// @Schema(description = "Modelo de datos de la entidad Usuario")
// @Data
// @Builder
// @AllArgsConstructor
// @NoArgsConstructor
// @Entity
// @Table(name = "users")
// public class User implements UserDetails {

// @Schema(description = "Identificador único del usuario")
// @Id
// @GeneratedValue(strategy = GenerationType.IDENTITY)
// private Long id;

// @Schema(description = "Área o servicio al que esta adscrito el usuario")
// @ManyToOne(fetch = FetchType.LAZY)
// @JoinColumn(name = "department_id", referencedColumnName = "id")
// private Department department;

// @Schema(description = "Profesional que supervisa al usuario si lo hubiere")
// @ManyToOne(fetch = FetchType.LAZY)
// @JoinColumn(name = "supervisor_id", referencedColumnName = "id")
// private Professional supervisor;

// @Schema(description = "Contraseña del usuario")
// @Column(name = "password", nullable = false)
// private String password;

// @Schema(description = "Nombre del usuario")
// @Column(name = "name", nullable = false)
// private String name;

// @Schema(description = "Apellidos del usuario")
// @Column(name = "surname", nullable = false)
// private String surname;

// @Schema(description = "Documento nacional de identidad o equivalente del
// usuario")
// @Column(name = "id_card", nullable = false)
// private String idCard;

// @Schema(description = "Tarjeta sanitaria del usuario")
// @Column(name = "health_card", nullable = false)
// private String healthCard;

// @Schema(description = "Correo eléctronico del usuario")
// @Column(name = "email", nullable = false)
// private String email;

// @Schema(description = "Teléfono de contacto del usuario")
// @Column(name = "phone", nullable = false)
// private Long phone;

// @Schema(description = "Consentimiento de tratamiento estadístico de datos del
// usuario")
// @Column(name = "data_consent", nullable = false)
// private Boolean dataConsent;

// @Schema(description = "Rango terapéutico de INR del usuario")
// @ManyToOne(fetch = FetchType.LAZY)
// @JoinColumn(name = "ranges_inr", referencedColumnName = "id")
// private RangeInr rangeInr;

// @Schema(description = "Patrón de dosificación del usuario")
// @ManyToOne(fetch = FetchType.LAZY)
// @JoinColumn(name = "dose_pattern_id", referencedColumnName = "id")
// private DosePattern dosePattern;

// @Schema(description = "Marca de tiempo de creación")
// @CreationTimestamp
// @Temporal(TemporalType.TIMESTAMP)
// @Column(name = "created_at", nullable = false, updatable = false)
// private LocalDateTime createdAt;

// @Schema(description = "Marca de tiempo de actualización")
// @UpdateTimestamp
// @Temporal(TemporalType.TIMESTAMP)
// @Column(name = "updated_at", nullable = false)
// private LocalDateTime updatedAt;

// @Schema(description = "Listado de medidas tomadas por el usuario")
// @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
// private List<Measurement> measurements;

// @Schema(description = "Listado de fechas previstas de toma de medida del
// usuario")
// @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
// private List<ExpectedMeasurementDate> expectedMeasurementDates;

// @Schema(description = "Listado de observaciones del usario")
// @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
// private List<Observation> observations;

// @Schema(description = "Listado de roles del usuario")
// @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
// @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id",
// referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name =
// "role_id", referencedColumnName = "id"))
// private Set<Role> roles;

// @Override
// public Collection<? extends GrantedAuthority> getAuthorities() {

// if (roles == null) {
// return Collections.emptyList(); // Return an empty list if roles is null
// }
// return roles.stream()
// .map(role -> new SimpleGrantedAuthority(role.toString()))
// .collect(Collectors.toList());
// }

// @Override
// public String getUsername() {
// return email;
// }

// @Override
// public String getPassword() {
// return password;
// }

// @Override
// public boolean isAccountNonExpired() {
// return true;
// }

// @Override
// public boolean isAccountNonLocked() {
// return true;
// }

// @Override
// public boolean isCredentialsNonExpired() {
// return true;
// }

// @Override
// public boolean isEnabled() {
// return true;
// }

// }
