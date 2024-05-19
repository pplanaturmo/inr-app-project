package com.pplanaturmo.inrappproject.department.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "DTO de validación de creación de departamento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequest {

    @Schema(description = "Nombre del departamento")
    @NotNull
    @NotBlank(message = "Department name is required")
    private String name;

    @Schema(description = "Ciudad del departamento")
    @NotNull
    @NotBlank(message = "Department city is required")
    private String city;

}
