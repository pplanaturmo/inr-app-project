package com.pplanaturmo.inrappproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

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

    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Measurement> measurements;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ExpectedMeasurementDate> expectedMeasurementDates;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Observation> observations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Role> roles;

}
