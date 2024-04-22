package com.pplanaturmo.inrappproject.model;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "measurements_dates")
public class ExpectedMeasurementDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "expected_date", nullable = false)
    private Date expectedDate;

    @Column(name = "fullfilled_date", nullable = true)
    private Date fullfilled_date;

    @Column(name = "fullfilled", nullable = false)
    private Boolean fulfilled;
}
