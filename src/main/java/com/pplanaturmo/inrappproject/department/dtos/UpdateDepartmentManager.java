package com.pplanaturmo.inrappproject.department.dtos;

import org.springframework.format.annotation.NumberFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "DTO de validación del coordinador de departamento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDepartmentManager {

    @Schema(description = "ID del departamento")
    @NumberFormat
    @NotNull(message = "User id is required")
    @NotBlank(message = "User id is required")
    private Long departmentId;

    @Schema(description = "ID del usuario a asignar al departamento")
    @NumberFormat
    @NotNull(message = "Manager id is required")
    @NotBlank(message = "Manager id is required")
    private Long managerId;

}
