package com.pplanaturmo.inrappproject.measurement;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.Date;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.pplanaturmo.inrappproject.alerts.Alert;
import com.pplanaturmo.inrappproject.dosePattern.DosePattern;
import com.pplanaturmo.inrappproject.user.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "measurements")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "value", nullable = false)
    private Double value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recommended_pattern", referencedColumnName = "id", nullable = false)
    private DosePattern recommendedPattern;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @OneToOne(mappedBy = "measurement", cascade = CascadeType.ALL)
    private Alert alert;
}

