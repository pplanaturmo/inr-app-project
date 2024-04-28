package com.pplanaturmo.inrappproject.measurement.dtos;

import java.util.Date;

import org.springframework.format.annotation.NumberFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementRequest {


    @NotNull(message = "Date  is required")
    private Date date;

    @NumberFormat
    @NotNull(message = "Value is required")
    @NotBlank(message = "Value is required")
    private Double value;

}
