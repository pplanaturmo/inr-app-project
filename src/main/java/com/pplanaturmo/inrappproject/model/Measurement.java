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
@Table(name = "measurements")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "date", nullable = false)
    private Timestamp date;

    @Column(name = "value", nullable = false)
    private Integer value;

    @Column(name = "recomended_level", nullable = false)
    private Integer recommendedLevel;

    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;
}

