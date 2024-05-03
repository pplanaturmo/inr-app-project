package com.pplanaturmo.inrappproject.aggregatedDtos;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatesBetweenDto {

    @NumberFormat
    @NotNull(message = "User id is required")
    private Long userId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotBlank(message = "Start date value is required in format yyyy-MM-dd")
    private String startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotBlank(message = "Finish date value is required in format yyyy-MM-dd")
    private String finishDate;
}
