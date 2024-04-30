package com.pplanaturmo.inrappproject.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.pplanaturmo.inrappproject.department.Department;
import com.pplanaturmo.inrappproject.dosePattern.DosePattern;
import com.pplanaturmo.inrappproject.expectedMeasurementDate.ExpectedMeasurementDate;
import com.pplanaturmo.inrappproject.measurement.Measurement;
import com.pplanaturmo.inrappproject.observation.Observation;
import com.pplanaturmo.inrappproject.professional.Professional;
import com.pplanaturmo.inrappproject.rangeInr.RangeInr;
import com.pplanaturmo.inrappproject.role.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

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
    @JoinColumn(name = "patterns", referencedColumnName = "id")
    private DosePattern dosePattern;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Measurement> measurements;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ExpectedMeasurementDate> expectedMeasurementDates;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Observation> observations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Role> roles;

    
}
