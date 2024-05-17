package com.pplanaturmo.inrappproject.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Schema(description = "The unique identifier of the user")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supervisor_id", referencedColumnName = "id")
    private Professional supervisor;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "id_card", nullable = false)
    private String idCard;

    @Column(name = "health_card", nullable = false)
    private String healthCard;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private Long phone;

    @Column(name = "data_consent", nullable = false)
    private Boolean dataConsent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ranges_inr", referencedColumnName = "id")
    private RangeInr rangeInr;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dose_pattern_id", referencedColumnName = "id")
    private DosePattern dosePattern;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Measurement> measurements;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ExpectedMeasurementDate> expectedMeasurementDates;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Observation> observations;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (roles == null) {
            return Collections.emptyList(); // Return an empty list if roles is null
        }
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.toString()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
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

}
