package com.pplanaturmo.inrappproject.dto;

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
public class DosageRequest {

    @NumberFormat
    @NotNull(message = "Measurement number is required")
    @NotBlank
    private Long measurementId;

    @NotNull(message = "Date is required")
    @NotBlank
    private Date doseDate;

    @NotNull(message = "Taken value is required")
    @NotBlank
    private Boolean taken;

}
