package com.pplanaturmo.inrappproject.dosePattern;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dose_patterns")
public class DosePattern {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private Double[] patternValue;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "drug", nullable = false)
    @Enumerated(EnumType.STRING)
    private DrugTypeEnum drug;

    public enum DrugTypeEnum {
        SINTROM_4_WARFARINA_10,
        SINTROM_1_WARFARINA_1_3_5
    }

    public DosePattern(Integer level, Double[] patternValue, DrugTypeEnum drug) {
        this.patternValue = patternValue;
        this.level = level;
        this.drug = drug;
    }
}
