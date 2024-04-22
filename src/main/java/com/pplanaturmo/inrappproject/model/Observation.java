package com.pplanaturmo.inrappproject.model;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "observations")
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_id", referencedColumnName = "id")
    private Measurement measurement;

    @Column(name = "date", nullable = false)
    private Timestamp date;

    @Enumerated(EnumType.STRING)
    @Column(name = "cause", nullable = false)
    private Cause cause;

    @Column(name = "description", nullable = false, columnDefinition = "LONGTEXT")
    private String description;

    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;

    public enum Cause {
        NO_DOSE,
        DOUBLE_DOSE,
        BLEEDING,
        DIET,
        DRUG,
        OTHER
    }
}
