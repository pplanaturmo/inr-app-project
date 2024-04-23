package com.pplanaturmo.inrappproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequest {

    @NotNull
    @NotBlank(message = "Department name is required")
    private String name;

    @NotNull
    @NotBlank(message = "Department city is required")
    private String city;

}
