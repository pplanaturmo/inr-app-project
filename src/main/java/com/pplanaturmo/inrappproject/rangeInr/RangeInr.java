package com.pplanaturmo.inrappproject.rangeInr;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "ranges_inr")
public class RangeInr {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "min_level", nullable = false)
    private Double minLevel;

    @Column(name = "max_level", nullable = false)
    private Double maxLevel;

    @Column(name = "description", nullable = false)
    private String description;
}
